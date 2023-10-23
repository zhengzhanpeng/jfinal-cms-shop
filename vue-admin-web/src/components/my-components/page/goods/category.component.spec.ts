import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CategoryComponent } from './category.component';

describe('CategoryComponent', () => {
  let component: CategoryComponent;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [CategoryComponent]
    });

    component = TestBed.createComponent(CategoryComponent).componentInstance;
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should fetch categories on initialization', () => {
    const mockCategories = [{ id: 1 }, { id: 2 }, { id: 3 }];

    component.ngOnInit();

    const req = httpMock.expectOne('/api/categories');
    expect(req.request.method).toBe('GET');
    req.flush(mockCategories);

    expect(component.categories).toEqual(mockCategories);
  });

  it('should add a category', () => {
    const mockCategory = { id: 4 };

    component.addCategory(mockCategory);

    const req = httpMock.expectOne('/api/categories');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockCategory);
    req.flush(null);

    expect(component.categories).toContain(mockCategory);
  });

  it('should delete a category', () => {
    const mockCategory = { id: 1 };

    component.deleteCategory(mockCategory);

    const req = httpMock.expectOne(`/api/categories/${mockCategory.id}`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);

    expect(component.categories).not.toContain(mockCategory);
  });

  it('should return the total number of categories', () => {
    component.categories = [{ id: 1 }, { id: 2 }, { id: 3 }];

    expect(component.totalCategories).toBe(3);
  });
});
