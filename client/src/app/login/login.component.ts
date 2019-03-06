import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LoginService} from './login.service';
import {map, tap} from 'rxjs/operators';
import {Router} from '@angular/router';
import {TokenHandlerService} from './token-handler.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formGroup: FormGroup;

  constructor(
    private fb: FormBuilder,
    private loginService: LoginService,
    private router: Router,
    private tokenHandlerService: TokenHandlerService
  ) { }

  ngOnInit() {
    this.formGroup = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  submit() {
    if (this.formGroup.valid) {
      this.loginService.login(this.formGroup.get('username').value, this.formGroup.get('password').value)
        .pipe(
          map(data => data.token),
          tap(this.tokenHandlerService.handle)
        )
        .subscribe(token => this.router.navigate(['/', 'backend', 'character']));
    }
  }
}
