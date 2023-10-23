import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { GoodsListComponent } from './goodslist.component';

describe('GoodsListComponent', () => {
  let component: GoodsListComponent;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [GoodsListComponent]
    });

    component = TestBed.createComponent(GoodsListComponent).componentInstance;
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should call getGoodsList on initialization', () => {
    spyOn(component, 'getGoodsList');

    component.ngOnInit();

    expect(component.getGoodsList).toHaveBeenCalled();
  });

  it('should fetch goods list', () => {
    const mockGoods = [{ id: 1 }, { id: 2 }, { id: 3 }];

    component.getGoodsList();

    const req = httpMock.expectOne('/api/goods');
    expect(req.request.method).toBe('GET');
    req.flush(mockGoods);

    expect(component.goodsList).toEqual(mockGoods);
  });

  it('should add goods', () => {
    const mockGoods = { id: 4 };

    spyOn(component, 'getGoodsList');

    component.addGoods(mockGoods);

    const req = httpMock.expectOne('/api/goods');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockGoods);
    req.flush(null);

    expect(component.getGoodsList).toHaveBeenCalled();
  });

  it('should delete goods', () => {
    const mockGoods = { id: 1 };

    spyOn(component, 'getGoodsList');

    component.deleteGoods(mockGoods);

    const req = httpMock.expectOne(`/api/goods/${mockGoods.id}`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);

    expect(component.getGoodsList).toHaveBeenCalled();
  });

  afterEach(() => {
    httpMock.verify();
  });
});
