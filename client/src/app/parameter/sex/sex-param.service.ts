import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SexParam} from './sex-param';

@Injectable({
  providedIn: 'root'
})
export class SexParamService {

  constructor(private http: HttpClient) {}

  fetch(): Observable<Array<SexParam>> {
    return this.http.get<Array<SexParam>>(`${location.protocol}/assets/parameter/sex.json`);
  }
}
