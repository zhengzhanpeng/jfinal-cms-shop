import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  categories: any[] = [];

  constructor() { }

  ngOnInit(): void {
    this.getCategories();
  }

  getCategories(): void {
    // Logic to fetch categories
  }

  addCategory(): void {
    // Logic to add category
  }

  deleteCategory(category: any): void {
    // Logic to delete category
  }

  get totalCategories(): number {
    return this.categories.length;
  }
}
</new_file>

<new_file file_path="vue-admin-web/src/components/my-components/page/goods/category.component.html">
<div *ngFor="let category of categories">
  <!-- content -->
</div>
<button (click)="addCategory()">Add Category</button>
</new_file>

<new_file file_path="vue-admin-web/src/components/my-components/page/goods/category.component.css">
/* Vue.js styles converted to Angular styles */
/* content */
