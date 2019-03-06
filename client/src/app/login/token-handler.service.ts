import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenHandlerService {

  constructor() { }

  handle(token: string) {
    let payload = token.split('.')[1];
    payload = payload.replace(/-/g, '+').replace(/_/g, '/');
    switch (payload.length % 4) {
      case 2: payload += '=='; break;
      case 3: payload += '='; break;
      default: break;
    }
    payload = decodeURIComponent(escape(atob(payload)));
    sessionStorage.setItem('token', token);
    sessionStorage.setItem('payload', payload);
  }
}
