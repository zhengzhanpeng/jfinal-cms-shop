import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-goods-edit',
  templateUrl: './goods-edit.component.html',
  styleUrls: ['./goods-edit.component.css']
})
export class GoodsEditComponent implements OnInit {
  product: Product = new Product();
  categories: Category[] = [];
  attributes: Attribute[] = [];

  constructor() {}

  ngOnInit() :void {
    this.productService.getProduct(this.productId).subscribe(product => this.product = product);
    this.categoryService.getCategories().subscribe(categories => this.categories = categories);
    this.attributeService.getAttributes().subscribe(attributes => this.attributes = attributes);
  }

  saveProduct() :void {
    this.productService.saveProduct(this.product).subscribe(() => this.goBack());
  }
  
  goBack() :void {
    this.location.back();
  }

  get productPrice() :number {
    return this.product.price * (1 - this.product.discount);
  }
  
  get productAttributes() :Attribute[] {
    return this.attributes.filter(attribute => attribute.productId === this.product.id);
  }
}
```
</new_file>

<new_file file_path="vue-admin-web/src/components/my-components/page/goods/goods-edit.component.html">
```html
<div *ngIf="dataProperty1">
  <!-- More converted Angular template from Vue.js template... -->
</div>
```
</new_file>

<new_file file_path="vue-admin-web/src/components/my-components/page/goods/goods-edit.component.css">
```css
/* Transferred styles from goodsEdit.vue */
.style1 {
  // Style rules...
}

.style2 {
.style1 {
  property1: value1;
  property2: value2;
  // More style rules...
}

.style2 {
  property1: value1;
  property2: value2;
  // More style rules...
}
// More transferred styles...
