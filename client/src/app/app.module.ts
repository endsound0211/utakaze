import { BrowserModule } from '@angular/platform-browser';
import {ErrorHandler, NgModule, NgZone} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {GlobalErrorHandlerService} from './global-error-handler.service';
import { GlobalModalComponent } from './global-modal/global-modal.component';
import {NgbModalModule} from '@ng-bootstrap/ng-bootstrap';
import {GlobalModalService} from './global-modal.service';

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
    NgbModalModule
  ],
  providers: [
    {provide: ErrorHandler, useClass: GlobalErrorHandlerService},
    GlobalModalService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
