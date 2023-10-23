import { Component, OnInit } from '@angular/core';
import { BusService } from '../bus.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  tagsList: any[] = [];
  collapse: boolean = false;

  constructor(private busService: BusService) { }

  ngOnInit(): void {
    this.busService.on('collapse').subscribe(msg => {
      this.collapse = msg;
    });

    this.busService.on('tags').subscribe(msg => {
      let arr = [];
      for(let i = 0, len = msg.length; i < len; i ++){
        msg[i].name && arr.push(msg[i].name);
      }
      this.tagsList = arr;
    });
  }
}
