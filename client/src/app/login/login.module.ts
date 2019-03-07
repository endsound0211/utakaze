import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {LoginService} from './login.service';
import { RegisterFormComponent } from './register-form/register-form.component';
import {NgbModalModule} from '@ng-bootstrap/ng-bootstrap';
import {AutoValidateModule} from 'es-ngx-auto-validate';

@NgModule({
  declarations: [LoginComponent, RegisterFormComponent],
  entryComponents: [RegisterFormComponent],
  imports: [
    CommonModule,
    LoginRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModalModule,
    AutoValidateModule
  ],
  providers: [
    LoginService
  ]
})
export class LoginModule { }
