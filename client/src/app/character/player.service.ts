import { Injectable } from '@angular/core';
import {SocketService} from '../backend/socket.service';

@Injectable()
export class PlayerService {

  constructor(private socketService: SocketService) { }

  fetch() {
    this.socketService.send('/backend/socket/server/utakaze/player/list');
  }

  join() {
    this.socketService.send('/backend/socket/server/utakaze/player/join');
  }

}
