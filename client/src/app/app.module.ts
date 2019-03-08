import { BrowserModule } from '@angular/platform-browser';
import {ErrorHandler, NgModule, NgZone} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {GlobalErrorHandlerService} from './global-error-handler.service';
import { GlobalModalComponent } from './global-modal/global-modal.component';
import {NgbModalModule} from '@ng-bootstrap/ng-bootstrap';
import {GlobalModalService} from './global-modal.service';
import {AutoValidateModule, DefaultErrorMessageMapZhTw, ERROR_MESSAGE_TOKEN} from 'es-ngx-auto-validate';
import {HttpClientModule} from '@angular/common/http';
import {GlobalAlertModule} from './global-alert/global-alert.module';

@NgModule({
  declarations: [
    AppComponent,
    GlobalModalComponent
  ],
  entryComponents: [
    GlobalModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModalModule,
    AutoValidateModule,
    HttpClientModule,
    GlobalAlertModule
  ],
  providers: [
    {provide: ERROR_MESSAGE_TOKEN, useValue: DefaultErrorMessageMapZhTw},
    {provide: ErrorHandler, useClass: GlobalErrorHandlerService},
    GlobalModalService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
