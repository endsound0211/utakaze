import { Injectable } from '@angular/core';
import {Character} from './character';
import {SocketService} from '../backend/socket.service';

@Injectable()
export class CharacterService {

  constructor(private socketService: SocketService) {
  }

  fetch() {
    this.socketService.send('/backend/socket/server/utakaze/character/list');
  }

  insert(character: Character) {
    this.socketService.send('/backend/socket/server/utakaze/character/insert', {}, JSON.stringify(this.genDataJson(character)));
  }

  update(character: Character) {
    this.socketService.send('/backend/socket/server/utakaze/character/update', {}, JSON.stringify(this.genDataJson(character)));
  }

  delete(character: Character) {
    this.socketService.send('/backend/socket/server/utakaze/character/delete', {}, JSON.stringify(this.genDataJson(character)));
  }

  genDataJson(character: Character): Character {
    return {
      id: character.id,
      belongUserId: character.belongUserId,
      data: JSON.stringify(character.data)
    }
  }
}
