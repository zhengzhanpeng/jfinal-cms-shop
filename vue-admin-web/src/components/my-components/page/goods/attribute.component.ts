import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-attribute',
  templateUrl: './attribute.component.html',
  styleUrls: ['./attribute.component.css']
})
export class AttributeComponent implements OnInit {
  attributes: any[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getAttributes();
  }

  getAttributes(): void {
    this.http.get('/api/attributes').subscribe(data => {
      this.attributes = data;
    });
  }

  addAttribute(attribute: any): void {
    this.http.post('/api/attributes', attribute).subscribe(() => {
      this.getAttributes();
    });
  }

  deleteAttribute(attribute: any): void {
    this.http.delete(`/api/attributes/${attribute.id}`).subscribe(() => {
      this.getAttributes();
    });
  }

  get totalAttributes(): number {
    return this.attributes.length;
  }
}
```
</new_file>

<new_file file="vue-admin-web/src/components/my-components/page/goods/attribute.component.spec.ts">
```typescript
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AttributeComponent } from './attribute.component';

describe('AttributeComponent', () => {
  let component: AttributeComponent;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [AttributeComponent]
    });

    component = TestBed.createComponent(AttributeComponent).componentInstance;
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should fetch attributes on initialization', () => {
    const mockAttributes = [{ id: 1 }, { id: 2 }, { id: 3 }];

    component.ngOnInit();

    const req = httpMock.expectOne('/api/attributes');
    expect(req.request.method).toBe('GET');
    req.flush(mockAttributes);

    expect(component.attributes).toEqual(mockAttributes);
  });

  it('should add an attribute', () => {
    const mockAttribute = { id: 4 };

    component.addAttribute(mockAttribute);

    const req = httpMock.expectOne('/api/attributes');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockAttribute);
    req.flush(null);

    expect(component.attributes).toContain(mockAttribute);
  });

  it('should delete an attribute', () => {
    const mockAttribute = { id: 1 };

    component.deleteAttribute(mockAttribute);

    const req = httpMock.expectOne(`/api/attributes/${mockAttribute.id}`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);

    expect(component.attributes).not.toContain(mockAttribute);
  });
});
