import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { BusService } from "../bus.service";

@Component({
  selector: "app-tags",
  templateUrl: "./tags.component.html",
  styleUrls: ["./tags.component.css"],
})
export class TagsComponent implements OnInit {
  tagsList: any[] = [];

  constructor(
    private busService: BusService,
    private router: Router,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.busService.on("tags").subscribe((msg) => {
      let arr = [];
      for (let i = 0, len = msg.length; i < len; i++) {
        msg[i].name && arr.push(msg[i].name);
      }
      this.tagsList = arr;
    });

    this.setTags(this.route.snapshot);
  }

  isActive(path: string): boolean {
    return path === this.router.url;
  }

  closeTags(index: number): void {
    const delItem = this.tagsList.splice(index, 1)[0];
    const item = this.tagsList[index]
      ? this.tagsList[index]
      : this.tagsList[index - 1];
    if (item) {
      delItem.path === this.router.url && this.router.navigate([item.path]);
    } else {
      this.router.navigate(["/"]);
    }
  }

  closeAll(): void {
    this.tagsList = [];
    this.router.navigate(["/"]);
  }

  closeOther(): void {
    const curItem = this.tagsList.filter((item) => {
      return item.path === this.router.url;
    });
    this.tagsList = curItem;
  }

  setTags(route: any): void {
    const isExist = this.tagsList.some((item) => {
      return item.path === route.url;
    });
    !isExist &&
      this.tagsList.push({
        title: route.data.title,
        path: route.url,
        name: route.component.name,
      });
    this.busService.emit("tags", this.tagsList);
  }

  handleTags(command: string): void {
    command === "other" ? this.closeOther() : this.closeAll();
  }

  get showTags(): boolean {
    return this.tagsList.length > 0;
  }
}
