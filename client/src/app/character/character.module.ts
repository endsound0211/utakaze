import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CharacterRoutingModule } from './character-routing.module';
import { IndexComponent } from './index/index.component';
import {CharacterService} from './character.service';
import {PlayerService} from './player.service';
import {ReactiveFormsModule} from '@angular/forms';
import { CharacterFormComponent } from './character-form/character-form.component';

@NgModule({
  declarations: [IndexComponent, CharacterFormComponent],
  imports: [
    CommonModule,
    CharacterRoutingModule,
    ReactiveFormsModule
  ],
  providers: [
    CharacterService,
    PlayerService
  ]
})
export class CharacterModule { }
