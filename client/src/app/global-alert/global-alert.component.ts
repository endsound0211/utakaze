import { Component, OnInit } from '@angular/core';
import {Alert} from './alert';
import {GlobalAlertService} from './global-alert.service';

@Component({
  selector: 'app-global-alert',
  templateUrl: './global-alert.component.html',
  styleUrls: ['./global-alert.component.css']
})
export class GlobalAlertComponent implements OnInit {
  alerts: Array<Alert>;

  constructor(private globalAlertService: GlobalAlertService) { }

  ngOnInit() {
    this.alerts = this.globalAlertService.alerts;
  }

}
