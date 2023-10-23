import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BusService {
  private subject = new Subject<any>();

  emitEvent(event: any) {
    this.subject.next(event);
  }

  getEvent(): Observable<any> {
    return this.subject.asObservable();
  }
}
