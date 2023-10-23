import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-goodslist',
  templateUrl: './goodslist.component.html',
  styleUrls: ['./goodslist.component.css']
})
export class GoodsListComponent implements OnInit {
  goodsList: any[] = [];

  constructor() { }

  ngOnInit(): void {
    this.getGoodsList();
  }

  getGoodsList(): void {
    // Logic to fetch goods list
  }

  addGoods(): void {
    // Logic to add goods
  }

  deleteGoods(goods: any): void {
    // Logic to delete goods
  }

  get totalGoods(): number {
    return this.goodsList.length;
  }
}
```
</new_file>

<new_file file_path="vue-admin-web/src/components/my-components/page/goods/goodslist.component.html">
```html
<div *ngFor="let goods of goodsList">
  <!-- content -->
</div>
<button (click)="addGoods()">Add Goods</button>
```
</new_file>

<new_file file_path="vue-admin-web/src/components/my-components/page/goods/goodslist.component.css">
```css
/* Vue.js styles converted to Angular styles */
/* content */
