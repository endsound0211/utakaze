import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {StarDiceParam} from './star-dice-param';

@Injectable({
  providedIn: 'root'
})
export class StarDiceParamService {

  constructor(private http: HttpClient) {}

  fetch(): Observable<Array<StarDiceParam>> {
    return this.http.get<Array<StarDiceParam>>(`${location.protocol}/assets/parameter/star-dice.json`);
  }
}
