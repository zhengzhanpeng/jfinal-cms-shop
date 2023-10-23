// attribute.component.ts
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-attribute',
  templateUrl: './attribute.component.html',
  styleUrls: ['./attribute.component.css']
})
export class AttributeComponent implements OnInit {
  // Vue.js data properties converted to Angular class properties
  attributes: any[] = [];

  constructor() { }

  ngOnInit(): void {
    // Vue.js created lifecycle hook converted to Angular ngOnInit lifecycle hook
    this.getAttributes();
  }

  // Vue.js methods converted to Angular class methods
  getAttributes(): void {
    // Logic to fetch attributes
  }

  addAttribute(): void {
    // Logic to add attribute
  }

  deleteAttribute(attribute: any): void {
    // Logic to delete attribute
  }

  // Vue.js computed properties converted to Angular getters
  get totalAttributes(): number {
    return this.attributes.length;
  }
}
```

```html
<!-- attribute.component.html -->
<!-- Vue.js template converted to Angular HTML template -->
<div *ngFor="let attribute of attributes">
  <!-- content -->
</div>
<button (click)="addAttribute()">Add Attribute</button>
```

```css
/* attribute.component.css */
/* Vue.js styles converted to Angular styles */
/* content */
