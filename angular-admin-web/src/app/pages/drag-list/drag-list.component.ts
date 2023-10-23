import { Component } from '@angular/core';

@Component({
  selector: 'app-drag-list',
  templateUrl: './drag-list.component.html',
  styleUrls: ['./drag-list.component.css']
})
export class DragListComponent {
  dragOptions = {
    animation: 120,
    scroll: true,
    group: 'sortlist',
    ghostClass: 'ghost-style'
  };

  todo = [
    {
      content: '开发图表组件'
    },
    {
      content: '开发拖拽组件'
    },
    {
      content: '开发权限测试组件'
    }
  ];

  doing = [
    {
      content: '开发登录注册页面'
    },
    {
      content: '开发头部组件'
    },
    {
      content: '开发表格相关组件'
    },
    {
      content: '开发表单相关组件'
    }
  ];

  done = [
    {
      content: '初始化项目，生成工程目录，完成相关配置'
    },
    {
      content: '开发项目整体框架'
    }
  ];

  removeHandle(event) {
    console.log(event);
    this.messageService.success(`从 ${event.from.id} 移动到 ${event.to.id} `);
  }
}
