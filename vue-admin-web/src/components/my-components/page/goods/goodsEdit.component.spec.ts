import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { GoodsEditComponent } from './goodsEdit.component';

describe('GoodsEditComponent', () => {
  let component: GoodsEditComponent;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [GoodsEditComponent]
    });

    component = TestBed.createComponent(GoodsEditComponent).componentInstance;
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should fetch goods on initialization', () => {
    const mockGoods = { stock: 100, isMarketable: true, introduction: "" };

    component.ngOnInit();

    const req = httpMock.expectOne('/api/goods');
    expect(req.request.method).toBe('GET');
    req.flush(mockGoods);

    expect(component.goods).toEqual(mockGoods);
  });

  it('should add goods', () => {
    const mockGoods = { id: 1 };

    component.addGoods(mockGoods);

    const req = httpMock.expectOne('/api/goods');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockGoods);
    req.flush(null);

    expect(component.goods).toEqual(mockGoods);
  });

  it('should delete goods', () => {
    const mockGoods = { id: 1 };

    component.deleteGoods(mockGoods);

    const req = httpMock.expectOne(`/api/goods/${mockGoods.id}`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);

    expect(component.goods).not.toEqual(mockGoods);
  });

  afterEach(() => {
    httpMock.verify();
  });
});
