import {Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LoginService} from '../login.service';
import {AutoValidateDirective} from 'es-ngx-auto-validate';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {
  @ViewChildren(AutoValidateDirective) autoValidates: QueryList<AutoValidateDirective>;
  formGroup: FormGroup;

  constructor(
    public activeModal: NgbActiveModal,
    private fb: FormBuilder,
    private loginService: LoginService
  ) { }

  ngOnInit() {
    this.formGroup = this.fb.group({
      username: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      name: ['', Validators.required]
    });
  }

  register() {
    if (this.formGroup.valid) {
      const user = this.formGroup.value;
      this.loginService.register(user)
        .subscribe(() => this.activeModal.close('register success'));
    } else {
      this.autoValidates.forEach((autoValidate) => autoValidate.checkError());
    }
  }
}
