// app.component.ts
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'jfinal-cms-shop';
  
  ngOnInit() {
    // converted Vue.js component logic here
  }
}
```

```html
<!-- app.component.html -->
<h1>{{ title }}</h1>
<!-- converted Vue.js template here -->
```

```css
/* app.component.css */
h1 {
  color: #42b983;
}
/* converted Vue.js styles here */
