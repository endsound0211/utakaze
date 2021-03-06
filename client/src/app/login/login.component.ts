import {Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LoginService} from './login.service';
import {map, tap} from 'rxjs/operators';
import {Router} from '@angular/router';
import {TokenHandlerService} from './token-handler.service';
import {User} from './user';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {RegisterFormComponent} from './register-form/register-form.component';
import {AutoValidateDirective} from 'es-ngx-auto-validate';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @ViewChildren(AutoValidateDirective) autoValidates: QueryList<AutoValidateDirective>;
  formGroup: FormGroup;

  constructor(
    private fb: FormBuilder,
    private loginService: LoginService,
    private router: Router,
    private tokenHandlerService: TokenHandlerService,
    private modalService: NgbModal
  ) { }

  ngOnInit() {
    this.formGroup = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    if (this.formGroup.valid) {
      this.loginService.login(this.formGroup.get('username').value, this.formGroup.get('password').value)
        .pipe(
          map(data => data.token),
          tap(this.tokenHandlerService.handle)
        )
        .subscribe(
          token => this.router.navigate(['/', 'backend', 'character']),
          error => this.formGroup.reset({username: '', password: ''})
        );
    } else {
      this.autoValidates.forEach((autoValidate) => autoValidate.checkError());
    }
  }

  openRegisterForm() {
    this.modalService.open(RegisterFormComponent, { size: 'lg' });
  }
}
