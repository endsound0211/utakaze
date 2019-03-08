import { Injectable } from '@angular/core';
import {Alert} from './alert';
import {Subject} from 'rxjs';
import {delay} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class GlobalAlertService {
  alerts: Array<Alert> = [];
  alertSubject: Subject<Alert> = new Subject<Alert>();

  constructor() {
    this.alertSubject.subscribe(alert => this.alerts.push(alert));
    this.alertSubject.pipe(
      delay(5000)
    ).subscribe(alert => this.alerts.splice(0, 1));
  }

  alertMessage(alert: Alert) {
    this.alertSubject.next(alert);
  }
}
