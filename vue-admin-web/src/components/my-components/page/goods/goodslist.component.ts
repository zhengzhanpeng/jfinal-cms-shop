import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-goodslist',
  templateUrl: './goodslist.component.html',
  styleUrls: ['./goodslist.component.css']
})
export class GoodsListComponent implements OnInit {
  goodsList: any[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getGoodsList();
  }

  getGoodsList(): void {
    this.http.get('/api/goods').subscribe(data => {
      this.goodsList = data;
    });
  }

  addGoods(goods: any): void {
    this.http.post('/api/goods', goods).subscribe(() => {
      this.getGoodsList();
    });
  }

  deleteGoods(goods: any): void {
    this.http.delete(`/api/goods/${goods.id}`).subscribe(() => {
      this.getGoodsList();
    });
  }
}
