import { Component, OnInit } from '@angular/core';
import {Alert} from './alert';
import {GlobalAlertService} from './global-alert.service';
import {transition, trigger, useAnimation} from '@angular/animations';
import {fadeInUp, fadeOut} from 'ng-animate';

@Component({
  selector: 'app-global-alert',
  templateUrl: './global-alert.component.html',
  styleUrls: ['./global-alert.component.css'],
  animations: [
    trigger('fade', [
      transition('void => *', useAnimation(fadeInUp, {params: {timing: 1}})),
      transition('* => void', useAnimation(fadeOut, {params: {timing: 1}}))
    ])
  ]
})
export class GlobalAlertComponent implements OnInit {
  alerts: Array<Alert>;
  fade: any;

  constructor(private globalAlertService: GlobalAlertService) { }

  ngOnInit() {
    this.alerts = this.globalAlertService.alerts;
  }

}
