import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  categories: any[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getCategories();
  }

  getCategories(): void {
    this.http.get('/api/categories').subscribe(data => {
      this.categories = data;
    });
  }

  addCategory(category: any): void {
    this.http.post('/api/categories', category).subscribe(() => {
      this.getCategories();
    });
  }

  deleteCategory(category: any): void {
    this.http.delete(`/api/categories/${category.id}`).subscribe(() => {
      this.getCategories();
    });
  }

  get totalCategories(): number {
    return this.categories.length;
  }
}
