import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GlobalAlertComponent } from './global-alert.component';
import {NgbAlertModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [GlobalAlertComponent],
  exports: [GlobalAlertComponent],
  imports: [
    CommonModule,
    NgbAlertModule
  ]
})
export class GlobalAlertModule { }
