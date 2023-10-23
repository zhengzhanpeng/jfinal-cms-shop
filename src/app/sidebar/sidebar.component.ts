import { Component, OnInit } from '@angular/core';
import { BusService } from '../bus.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  collapse: boolean = false;

  constructor(private busService: BusService) { }

  ngOnInit(): void {
    this.busService.on('collapse').subscribe(msg => {
      this.collapse = msg;
    });
  }
}
