import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HeaderComponent } from './header.component';
import { BusService } from '../bus.service';
import { Router } from '@angular/router';

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;
  let busService: BusService;
  let router: Router;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HeaderComponent ],
      providers: [ BusService, { provide: Router, useValue: jasmine.createSpyObj('Router', ['navigate']) } ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    busService = TestBed.inject(BusService);
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should interact with BusService when collapse event is emitted', () => {
    spyOn(busService, 'on').and.callThrough();
    busService.emit('collapse', true);
    expect(component.collapse).toBe(true);
  });

  it('should interact with BusService when handleSelect is called', () => {
    spyOn(busService, 'emit').and.callThrough();
    component.handleSelect('dashboard');
    expect(busService.emit).toHaveBeenCalledWith('menus', [{ icon: 'el-icon-setting', index: 'dashboard', title: '系统首页' }]);
  });

  it('should update state and view when handleCommand is called with loginout', () => {
    spyOn(localStorage, 'removeItem');
    component.handleCommand('loginout');
    expect(localStorage.removeItem).toHaveBeenCalledWith('ms_username');
    expect(router.navigate).toHaveBeenCalledWith(['/login']);
  });

  it('should update state when collapseChange is called', () => {
    spyOn(busService, 'emit').and.callThrough();
    component.collapseChange();
    expect(component.collapse).toBe(true);
    expect(busService.emit).toHaveBeenCalledWith('collapse', true);
  });

  it('should update state and view when handleFullScreen is called', () => {
    const fullscreenElement = {
      requestFullscreen: jasmine.createSpy('requestFullscreen'),
      exitFullscreen: jasmine.createSpy('exitFullscreen')
    };
    spyOn(document, 'exitFullscreen').and.callFake(fullscreenElement.exitFullscreen);
    spyOn(document, 'requestFullscreen').and.callFake(fullscreenElement.requestFullscreen);
    component.handleFullScreen();
    expect(component.fullscreen).toBe(true);
    expect(fullscreenElement.requestFullscreen).toHaveBeenCalled();
  });
});
