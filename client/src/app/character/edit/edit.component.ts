import { Component, OnInit } from '@angular/core';
import {Character} from '../character';
import {IndexComponent} from '../index/index.component';
import {CharacterService} from '../character.service';
import {ActivatedRoute, Router} from '@angular/router';
import {GlobalModalService} from '../../global-modal.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  character: Character;

  constructor(
    private indexComponent: IndexComponent,
    private characterService: CharacterService,
    private router: Router,
    private route: ActivatedRoute,
    private globalModalService: GlobalModalService
  ) { }

  ngOnInit() {
    this.indexComponent.selectedCharacter$
      .subscribe(character => this.character = character);
  }

  insert(character: Character): void {
    this.characterService.insert(character);
  }

  update(character: Character): void {
    this.characterService.update(character);
  }

  delete(character: Character): void {
    this.globalModalService.openConfirmAlert('確認刪除角色', () => {
      this.character = null;
      this.characterService.delete(character);
      this.router.navigate(['..'], {relativeTo: this.route});
    });
  }

}
