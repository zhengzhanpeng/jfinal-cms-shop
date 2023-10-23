import { Component, OnInit, OnDestroy, Input } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import tinymce from "tinymce/tinymce";

@Component({
    selector: "app-tinymce",
    template: `<textarea id="{{ id }}" [(ngModel)]="value"></textarea>`,
})
export class TinymceComponent implements OnInit, OnDestroy {
    @Input() value: string;
    @Input() setting: any;
    status = 0;
    id = "editor-" + new Date().getMilliseconds();

    constructor(private http: HttpClient) {}

    ngOnInit() {
        const setting = {
            ...this.setting,
            selector: "#" + this.id,
            init_instance_callback: (editor: any) => {
                editor.on("input change undo redo", () => {
                    this.value = editor.getContent();
                });
            },
            images_upload_handler: (
                blobInfo: any,
                success: any,
                failure: any,
            ) => {
                if (blobInfo.blob().size > this.setting.maxSize) {
                    failure("文件体积过大");
                } else {
                    const formData = new FormData();
                    formData.append(
                        "file",
                        blobInfo.blob(),
                        blobInfo.filename(),
                    );
                    this.http.post("uploadPath", formData).subscribe(
                        (res: any) => success(res.url),
                        () => failure("上传失败"),
                    );
                }
            },
        };
        tinymce.init(setting);
    }

    ngOnDestroy() {
        tinymce.get(this.id).destroy();
    }
}
