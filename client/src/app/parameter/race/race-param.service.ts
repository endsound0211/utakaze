import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {RaceParam} from './race-param';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RaceParamService {

  constructor(private http: HttpClient) {}

  fetch(): Observable<Array<RaceParam>> {
    return this.http.get<Array<RaceParam>>(`${location.protocol}/assets/parameter/race.json`);
  }
}
