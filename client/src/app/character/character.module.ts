import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CharacterRoutingModule } from './character-routing.module';
import { IndexComponent } from './index/index.component';
import {CharacterService} from './character.service';
import {PlayerService} from './player.service';
import {ReactiveFormsModule} from '@angular/forms';
import { CharacterFormComponent } from './character-form/character-form.component';
import {NgbAccordionModule, NgbButtonsModule} from '@ng-bootstrap/ng-bootstrap';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EditComponent } from './edit/edit.component';

@NgModule({
  declarations: [IndexComponent, CharacterFormComponent, DashboardComponent, EditComponent],
  imports: [
    CommonModule,
    CharacterRoutingModule,
    ReactiveFormsModule,
    NgbAccordionModule,
    NgbButtonsModule
  ],
  providers: [
    CharacterService,
    PlayerService
  ]
})
export class CharacterModule { }
