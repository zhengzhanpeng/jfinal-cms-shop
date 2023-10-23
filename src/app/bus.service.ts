import { Injectable } from "@angular/core";
import { Subject } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class BusService {
  private events = new Map<string, Subject<any>>();

  emit(event: string, data: any) {
    if (!this.events.has(event)) {
      this.events.set(event, new Subject<any>());
    }
    this.events.get(event).next(data);
  }

  on(event: string) {
    if (!this.events.has(event)) {
      this.events.set(event, new Subject<any>());
    }
    return this.events.get(event).asObservable();
  }
}
