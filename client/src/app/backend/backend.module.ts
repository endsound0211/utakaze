import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BackendRoutingModule } from './backend-routing.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { BackendComponent } from './backend.component';
import {TokenHttpInterceptorService} from './token-http-interceptor.service';

@NgModule({
  declarations: [BackendComponent],
  imports: [
    CommonModule,
    BackendRoutingModule,
    HttpClientModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: TokenHttpInterceptorService, multi: true}
  ]
})
export class BackendModule { }
