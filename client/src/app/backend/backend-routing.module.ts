import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BackendComponent} from './backend.component';

const routes: Routes = [
  {path: '', component: BackendComponent, children: [
      {path: 'character', loadChildren: '../character/character.module#CharacterModule'}
    ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BackendRoutingModule { }
