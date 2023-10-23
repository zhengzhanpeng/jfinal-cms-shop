import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-goods-edit',
  templateUrl: './goodsEdit.component.html',
  styleUrls: ['./goodsEdit.component.css']
})
export class GoodsEditComponent implements OnInit {
  goods: any = {stock:100,isMarketable:true,introduction:""};
  categorys: any[] = [];
  parameterValue: any[] = [];
  attributes: any[] = [];
  attributesStr: any[] = [];
  checkedAttributes: any[] = [];
  checkedAttributesStr: any[] = [];
  checkedAttributesArr: any[] = [];
  specifications: any[] = [];
  specificationsTableData: any[] = [];
  selectedSpecification: any = {};

  constructor() { }

  ngOnInit(): void {
    this.getGoods();
    this.getCategorys();
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
    // Logic to fetch goods
  }

  getCategorys(): void {
    // Logic to fetch categorys
  }

  getParameterValue(): void {
    // Logic to fetch parameterValue
  }

  getAttributes(): void {
    // Logic to fetch attributes
  }

  getAttributesStr(): void {
    // Logic to fetch attributesStr
  }

  getCheckedAttributes(): void {
    // Logic to fetch checkedAttributes
  }

  getCheckedAttributesStr(): void {
    // Logic to fetch checkedAttributesStr
  }

  getCheckedAttributesArr(): void {
    // Logic to fetch checkedAttributesArr
  }

  getSpecifications(): void {
    // Logic to fetch specifications
  }

  getSpecificationsTableData(): void {
    // Logic to fetch specificationsTableData
  }

  getSelectedSpecification(): void {
    // Logic to fetch selectedSpecification
  }

  addGoods(): void {
    // Logic to add goods
  }

  deleteGoods(goods: any): void {
    // Logic to delete goods
  }

  get totalGoods(): number {
    return this.goods.length;
  }
}
```
</new_file>

<new_file file_path="vue-admin-web/src/components/my-components/page/goods/goodsEdit.component.html">
```html
<!-- Vue.js template converted to Angular HTML template -->
<div *ngFor="let goods of goods">
  <!-- content -->
</div>
<button (click)="addGoods()">Add Goods</button>
```
</new_file>

<new_file file_path="vue-admin-web/src/components/my-components/page/goods/goodsEdit.component.css">
```css
/* Vue.js styles converted to Angular styles */
/* content */
