import { TestBed, inject } from '@angular/core/testing';
import { BusService } from './bus.service';

describe('BusService', () => {
  let service: BusService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BusService]
    });
    service = TestBed.inject(BusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should emit events correctly', (done: DoneFn) => {
    service.emit('testEvent', 'testData');
    service.on('testEvent').subscribe(data => {
      expect(data).toBe('testData');
      done();
    });
  });

  it('should listen to events correctly', (done: DoneFn) => {
    service.emit('testEvent', 'testData1');
    service.emit('testEvent', 'testData2');
    let count = 0;
    service.on('testEvent').subscribe(data => {
      count++;
      if (count === 1) {
        expect(data).toBe('testData1');
      } else if (count === 2) {
        expect(data).toBe('testData2');
        done();
      }
    });
  });

  it('should return an Observable', () => {
    const observable = service.on('testEvent');
    expect(observable.subscribe).toBeTruthy();
  });

  it('should create a new Subject for a new event', () => {
    const eventCount = service['events'].size;
    service.emit('newEvent', 'testData');
    expect(service['events'].size).toBe(eventCount + 1);
  });
});
