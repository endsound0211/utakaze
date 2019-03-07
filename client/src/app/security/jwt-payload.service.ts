import { Injectable } from '@angular/core';
import {JwtPayload} from './jwt-payload';
import {Router} from '@angular/router';
import {User} from './user';

@Injectable({
  providedIn: 'root'
})
export class JwtPayloadService {
  jwtPayload: JwtPayload;

  constructor(private router: Router) {
    const jwtPayloadJson = sessionStorage.getItem('payload');
    if (!jwtPayloadJson) {
      this.router.navigate(['/', 'login']);
    } else {
      this.jwtPayload = JSON.parse(jwtPayloadJson);
    }
  }

  get user(): User {
    return this.jwtPayload.data;
  }
}
