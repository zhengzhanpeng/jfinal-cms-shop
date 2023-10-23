import { Component, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Component({
    selector: "app-goods-edit",
    templateUrl: "./goodsEdit.component.html",
    styleUrls: ["./goodsEdit.component.css"],
})
export class GoodsEditComponent implements OnInit {
    goods: any = { stock: 100, isMarketable: true, introduction: "" };
    categories: any[] = [];
    parameterValue: any[] = [];
    attributes: any[] = [];
    attributesStr: any[] = [];
    checkedAttributes: any[] = [];
    checkedAttributesStr: any[] = [];
    checkedAttributesArr: any[] = [];
    specifications: any[] = [];
    specificationsTableData: any[] = [];
    selectedSpecification: any = {};

    constructor(private http: HttpClient) {}

    ngOnInit(): void {
        this.getGoods();
        this.getCategories();
        this.getParameterValue();
        this.getAttributes();
        this.getAttributesStr();
        this.getCheckedAttributes();
        this.getCheckedAttributesStr();
        this.getCheckedAttributesArr();
        this.getSpecifications();
        this.getSpecificationsTableData();
        this.getSelectedSpecification();
    }

    getGoods(): void {
        this.http.get("/api/goods").subscribe((data) => {
            this.goods = data;
        });
    }

    getCategories(): void {
        this.http.get("/api/categories").subscribe((data) => {
            this.categories = data;
        });
    }

    getParameterValue(): void {
        this.http.get("/api/parameterValue").subscribe((data) => {
            this.parameterValue = data;
        });
    }

    getAttributes(): void {
        this.http.get("/api/attributes").subscribe((data) => {
            this.attributes = data;
        });
    }

    getAttributesStr(): void {
        this.http.get("/api/attributesStr").subscribe((data) => {
            this.attributesStr = data;
        });
    }

    getCheckedAttributes(): void {
        this.http.get("/api/checkedAttributes").subscribe((data) => {
            this.checkedAttributes = data;
        });
    }

    getCheckedAttributesStr(): void {
        this.http.get("/api/checkedAttributesStr").subscribe((data) => {
            this.checkedAttributesStr = data;
        });
    }

    getCheckedAttributesArr(): void {
        this.http.get("/api/checkedAttributesArr").subscribe((data) => {
            this.checkedAttributesArr = data;
        });
    }

    getSpecifications(): void {
        this.http.get("/api/specifications").subscribe((data) => {
            this.specifications = data;
        });
    }

    getSpecificationsTableData(): void {
        this.http.get("/api/specificationsTableData").subscribe((data) => {
            this.specificationsTableData = data;
        });
    }

    getSelectedSpecification(): void {
        this.http.get("/api/selectedSpecification").subscribe((data) => {
            this.selectedSpecification = data;
        });
    }

    addGoods(goods: any): void {
        this.http.post("/api/goods", goods).subscribe(() => {
            this.getGoods();
        });
    }

    deleteGoods(goods: any): void {
        this.http.delete(`/api/goods/${goods.id}`).subscribe(() => {
            this.getGoods();
        });
    }
}
