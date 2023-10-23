import {
    Component,
    Input,
    Output,
    EventEmitter,
    OnChanges,
    SimpleChanges,
} from "@angular/core";

@Component({
    selector: "app-blockquote",
    template: `
        <div class="elem-quote" [style.border-left-color]="cl">
            <ng-content></ng-content>
        </div>
    `,
    styles: [
        `
            .elem-quote {
                width: 100%;
                padding: 15px;
                border-left: 5px solid #009688;
            }
        `,
    ],
})
export class BlockquoteComponent implements OnChanges {
    @Input() color: string;
    @Output() update = new EventEmitter<string>();
    cl: string;

    ngOnChanges(changes: SimpleChanges) {
        if (changes.color) {
            this.cl = "border-left-color:" + this.color;
            this.update.emit(this.cl);
        }
    }
}
