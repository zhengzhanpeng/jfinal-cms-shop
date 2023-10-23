import { Component, Input, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Component({
    selector: "app-pictureview",
    templateUrl: "./pictureview.component.html",
    styleUrls: ["./pictureview.component.css"],
})
export class PictureviewComponent implements OnInit {
    @Input() type: string;
    @Input() selectedImg: string;
    dialogVisible = false;
    attachmentList = [];
    dataPage = {
        pageNumber: 1,
        pageSize: 20,
    };

    constructor(private http: HttpClient) {}

    ngOnInit() {
        this.getAttachmentList();
    }

    handleSizeChange(val: number) {
        console.log(`每页 ${val} 条`);
    }

    handleCurrentChange(val: number) {
        console.log(`当前页: ${val}`);
    }

    getAttachmentList(
        pageNum = this.dataPage.pageNumber,
        pageSize = this.dataPage.pageSize,
    ) {
        this.http
            .get(
                `/file/getAttachmentList?pageNum=${pageNum}&pageSize=${pageSize}`,
            )
            .subscribe((res: any) => {
                this.dataPage = res.data;
                this.attachmentList = res.data.list;
            });
    }

    delAttachment(index: number, item: any) {
        this.http
            .get(`/file/delAttachment?id=${item.id}`)
            .subscribe((res: any) => {
                if (res.code) {
                    this.attachmentList.splice(index, 1);
                    console.log("刪除成功");
                } else {
                    console.log("禁止刪除");
                }
            });
    }

    selectImg(index: number, item: any) {
        this.attachmentList[index].checked =
            !this.attachmentList[index].checked;
    }

    selectChange() {
        this.dialogVisible = false;
        const data = this.attachmentList.filter((row) => row.checked);
        console.log(data);
    }

    handleUploadSuccess(res: any, file: any) {
        const attachment = res.attachment;
        attachment.thumbnail_temp = res.url;
        this.attachmentList.unshift(attachment);
    }
}
