<div class="header">
    <div class="collapse-btn" (click)="collapseChange()">
        <i class="el-icon-menu"></i>
    </div>
    <div class="logo">后台管理系统</div>
    <div class="menu-box">
        <el-menu class="el-menu-demo"
                 mode="horizontal"
                 (select)="handleSelect($event)"
                 backgroundColor="#242f42"
                 textColor="#fff"
                 activeTextColor="#1AA094">
            <el-submenu *ngFor="let item of items; let i = index" [index]="'menu_'+item.lanmuId">
                <template #title>
                    <i [class]="item.icon"></i><span>{{ item.title }}</span>
                </template>
                <el-menu-item *ngFor="let subItem of item.subs" [index]="'menu_'+subItem.lanmuId" [route]="{ path: '/'+subItem.index,query:{lanmuId:subItem.lanmuId,title:subItem.title}}">
                    {{ subItem.title }}
                </el-menu-item>
            </el-submenu>
        </el-menu>
    </div>
    <div class="header-right">
        <div class="header-user-con">
            <div class="btn-fullscreen" (click)="handleFullScreen()">
                <el-tooltip effect="dark" [content]="fullscreen?'取消全屏':'全屏'" placement="bottom">
                    <i class="el-icon-rank"></i>
                </el-tooltip>
            </div>
            <div class="btn-bell">
                <el-tooltip effect="dark" [content]="message?'有'+message+'条未读消息':'消息中心'" placement="bottom">
                    <a routerLink="/tabs">
                        <i class="el-icon-bell"></i>
                    </a>
                </el-tooltip>
                <span class="btn-bell-badge" *ngIf="message"></span>
            </div>
            <div class="user-avator"><img src="static/img/img.jpg"></div>
            <el-dropdown class="user-name" trigger="click" (command)="handleCommand($event)">
                <span class="el-dropdown-link">
                    {{user.nickname}} <i class="el-icon-caret-bottom"></i>
                </span>
                <el-dropdown-menu #dropdown>
                    <a href="https://gitee.com/lmhc/jfinal-cms-shop/tree/master" target="_blank">
                        <el-dropdown-item>项目仓库</el-dropdown-item>
                    </a>
                    <el-dropdown-item divided  command="loginout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </div>
</div>
</new_file>

<new_file file_path="src/app/header/header.component.ts">
import { Component, OnInit } from '@angular/core';
import { BusService } from '../bus.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  collapse: boolean = false;
  fullscreen: boolean = false;
  message: number = 2;
  user: any = {};

  constructor(private busService: BusService){}

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  handleCommand(command: string){
  if (command === 'loginout') {
    localStorage.removeItem('ms_username');
    this.router.navigate(['/login']);
  }
  }

  handleSelect(index: string){
  console.log(index);
  var items = [
    {
      icon: 'el-icon-setting',
      index: 'dashboard',
      title: '系统首页'
    }
  ]
  this.busService.emit('menus', items);
  }

  collapseChange(){
  this.collapse = !this.collapse;
  this.busService.emit('collapse', this.collapse);
  }

  handleFullScreen(){
  let element = document.documentElement;
  if (this.fullscreen) {
    if (document.exitFullscreen) {
      document.exitFullscreen();
    } else if (document.webkitCancelFullScreen) {
      document.webkitCancelFullScreen();
    } else if (document.mozCancelFullScreen) {
      document.mozCancelFullScreen();
    } else if (document.msExitFullscreen) {
      document.msExitFullscreen();
    }
  } else {
    if (element.requestFullscreen) {
      element.requestFullscreen();
    } else if (element.webkitRequestFullScreen) {
      element.webkitRequestFullScreen();
    } else if (element.mozRequestFullScreen) {
      element.mozRequestFullScreen();
    } else if (element.msRequestFullscreen) {
      element.msRequestFullscreen();
    }
  }
  this.fullscreen = !this.fullscreen;
  }
}
