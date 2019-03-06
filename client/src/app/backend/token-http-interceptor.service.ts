import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {TokenHandlerService} from '../login/token-handler.service';

@Injectable({
  providedIn: 'root'
})
export class TokenHttpInterceptorService implements HttpInterceptor {

  constructor(private tokenHandlerService: TokenHandlerService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = sessionStorage.getItem('token');
    return next.handle(req.clone({
      setHeaders: {token}
    })).pipe(tap(event => {
      if (event instanceof HttpResponse) {
        if (event.headers.has('token')) {
          this.tokenHandlerService.handle(event.headers.get('token'));
        }
      }
    }));
  }
}
