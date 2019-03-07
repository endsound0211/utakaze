import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {User} from './user';
import {Observable} from 'rxjs';

@Injectable()
export class LoginService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    return this.http.post<any>(`${environment.apiHost}/oauth/login`,
      `username=${username}&password=${encodeURIComponent(password)}`,
      {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
  }

  register(user: User): Observable<void> {
    return this.http.post<void>(`${environment.apiHost}/api/user/register`, user);
  }
}
