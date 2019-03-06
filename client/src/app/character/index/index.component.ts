import {Component, OnDestroy, OnInit} from '@angular/core';
import {CharacterService} from '../character.service';
import {SocketService} from '../../backend/socket.service';
import {PlayerService} from '../player.service';
import {Player} from '../player';
import {Character} from '../character';
import {from} from 'rxjs';
import {groupBy, mergeMap, reduce, tap} from 'rxjs/operators';


@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit, OnDestroy {
  players: Array<Player>;
  character: Character;

  constructor(
    private socketService: SocketService,
    private characterService: CharacterService,
    private playerService: PlayerService
  ) { }

  ngOnInit() {
    // subscribe player
    this.socketService.subscribe('/user/backend/socket/client/utakaze/player/list', (data) => {
      this.players = JSON.parse(data.body);
      this.players.forEach(player => player.characters = new Array<Character>());
      this.characterService.fetch();
    });

    this.socketService.subscribe('/user/backend/socket/client/utakaze/player/join', (data) => {
      const player: Player = JSON.parse(data.body);
      if (!this.players.find(p => p.id === player.id)) {
        this.players.push(player);
      }
    });

    // subscribe character
    this.socketService.subscribe('/user/backend/socket/client/utakaze/character/list', (data) => {
      const characters: Array<Character> = JSON.parse(data.body);
      from(characters)
        .pipe(
          tap(character => character.data = JSON.parse(character.data)),
          groupBy(character => character.belongUserId),
          mergeMap((group$) => group$.pipe(reduce((acc: any, cur) => [...acc, cur], []))))
        .subscribe((cs: Array<Character>) => {
          const key = cs[0].belongUserId;
          const player = this.players.find((p) => p.id === key);
          player.characters = player.characters.concat(cs);
        });
    });
    this.socketService.subscribe('/backend/socket/client/utakaze/character/insert', (data) => {
      const character: Character = JSON.parse(data.body);
      character.data = JSON.parse(character.data);
      const player = this.players.find(p => p.id === character.belongUserId);
      if (player) {
        player.characters.push(character);
        this.character = character;
      }
    });
    this.socketService.subscribe('/backend/socket/client/utakaze/character/update', (data) => {
      const character: Character = JSON.parse(data.body);
      character.data = JSON.parse(character.data);
      const player = this.players.find(p => p.id === character.belongUserId);
      if (player) {
        const oldCharacter: Character = player.characters.find(c => c.id === character.id);
        if (oldCharacter) {
          oldCharacter.data = character.data;
        }
      }
    });
    this.socketService.subscribe('/backend/socket/client/utakaze/character/delete', (data) => {
      const character: Character = JSON.parse(data.body);
      character.data = JSON.parse(character.data);
      const player = this.players.find(p => p.id === character.belongUserId);
      if (player) {
        const oldIndex = player.characters.findIndex(c => c.id === character.id);
        if (oldIndex !== -1) {
          player.characters.splice(oldIndex, 1);
        }
      }
    });


    // this.characterService.fetch();
    this.playerService.fetch();
  }

  ngOnDestroy(): void {
  }

  insert(character: Character): void {
    this.characterService.insert(character);
  }

  update(character: Character): void {
    this.characterService.update(character);
  }

  delete(character: Character): void {
    this.hide();
    this.characterService.delete(character);
  }

  trackPlayerById(index, player) {
    return player.id;
  }

  trackCharacterById(index, character) {
    return character.id;
  }

  show(character: Character) {
    this.character = character;
  }

  hide() {
    this.character = null;
  }

  newCharacter() {
    this.character = new Character();
  }
}
