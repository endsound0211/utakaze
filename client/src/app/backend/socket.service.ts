import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {environment} from '../../environments/environment';
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {messageCallbackType} from '@stomp/stompjs';
import {StompHeaders} from '@stomp/stompjs';
import {StompSubscription} from '@stomp/stompjs';

@Injectable({
  providedIn: 'root'
})
export class SocketService {
  stompClient = null;
  connected$ = new BehaviorSubject<boolean>(false);

  constructor() { }

  connect() {
    const socket = new SockJS(`${environment.apiHost}/socket?token=${sessionStorage.getItem('token')}`);
    this.stompClient = Stomp.Stomp.over(socket);

    this.stompClient.connect({}, (frame) => {
      this.connected$.next(true);
    });
  }

  send(destination: string, header?: any, body?: string) {
    this.stompClient.send(destination, header, body);
  }

  subscribe(destination: string, callback: messageCallbackType, headers?: StompHeaders): StompSubscription {
    return this.stompClient.subscribe(destination, callback, headers);
  }

  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }

    this.connected$.next(false);
  }
}
