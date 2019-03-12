import {Component, OnDestroy, OnInit} from '@angular/core';
import {CharacterService} from '../character.service';
import {SocketService} from '../../backend/socket.service';
import {PlayerService} from '../player.service';
import {Player} from '../player';
import {Character} from '../character';
import {from, Subject} from 'rxjs';
import {groupBy, mergeMap, reduce, tap} from 'rxjs/operators';
import {JwtPayloadService} from '../../security/jwt-payload.service';
import {User} from '../../security/user';
import {GlobalAlertService} from '../../global-alert/global-alert.service';
import {ActivatedRoute, Router} from '@angular/router';


@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit, OnDestroy {
  players: Array<Player>;
  selectedCharacter$: Subject<Character> = new Subject<Character>();
  user: User;

  constructor(
    private socketService: SocketService,
    private characterService: CharacterService,
    private playerService: PlayerService,
    private jwtPayloadService: JwtPayloadService,
    private globalAlertService: GlobalAlertService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.user = this.jwtPayloadService.user;
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
      this.globalAlertService.alertMessage({type: 'info', message: `${player.name}加入`});
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
        this.selectedCharacter$.next(character);
        this.globalAlertService.alertMessage({type: 'info', message: `${player.name}新建角色:${character.data.name}`});
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
          this.globalAlertService.alertMessage({type: 'info', message: `${player.name}更新角色:${character.data.name}`});
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
          this.globalAlertService.alertMessage({type: 'danger', message: `${player.name}刪除角色:${character.data.name}`});
        }
      }
    });


    // this.characterService.fetch();
    this.playerService.fetch();
  }

  ngOnDestroy(): void {
  }

  insert(): void {
    this.selectedCharacter$.next(new Character());
    this.router.navigate(['.', 'edit'], {
      relativeTo: this.route,
    });
  }

  update(character: Character) {
    this.selectedCharacter$.next(character);
    this.router.navigate(['.', 'edit'], {
      relativeTo: this.route,
    });
  }

  trackPlayerById(index, player) {
    return player.id;
  }

  trackCharacterById(index, character) {
    return character.id;
  }

  hide() {
    // this.character = null;
  }
}
