import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Character} from '../character';
import {FormBuilder, FormGroup} from '@angular/forms';
import {RaceParamService} from '../../parameter/race/race-param.service';
import {RaceParam} from '../../parameter/race/race-param';
import {SexParam} from '../../parameter/sex/sex-param';
import {SexParamService} from '../../parameter/sex/sex-param.service';

@Component({
  selector: 'app-character-form',
  templateUrl: './character-form.component.html',
  styleUrls: ['./character-form.component.css']
})
export class CharacterFormComponent implements OnInit, OnChanges {
  @Input()
  character: Character;
  formGroup: FormGroup;

  @Output()
  onInsert = new EventEmitter<Character>();
  @Output()
  onUpdate = new EventEmitter<Character>();
  @Output()
  onDelete = new EventEmitter<Character>();

  raceParams: Array<RaceParam>;
  sexParams: Array<SexParam>;

  constructor(
    private fb: FormBuilder,
    private raceParamService: RaceParamService,
    private sexParamService: SexParamService
  ) {
    this.formGroup = this.fb.group({
      id: [null],
      data: this.fb.group({
        name: [null],
        race: [null],
        sex: [null],
        age: [null],
        height: [null],
        hairColor: [null],
        eyeColor: [null],
        skinColor: [null],
        headOrnaments: [null],
        bodyOrnaments: [null],
        meleeWeapon: [null],
        rangedWeapon: [null],
        musicalInstrument: [null],
        belongLocation: [null],
        belongGroup: [null],
        career: [null]
      }),
      belongUserId: [null]
    });
  }

  ngOnInit() {
    this.raceParamService.fetch()
      .subscribe((params) => this.raceParams = params);
    this.sexParamService.fetch()
      .subscribe((params) => this.sexParams = params);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if ('character' in changes) {
      this.formGroup.patchValue(changes.character.currentValue);
    }
  }

  insert() {
    this.character = this.formGroup.value;
    this.onInsert.emit(this.character);
  }

  update() {
    this.character = this.formGroup.value;
    this.onUpdate.emit(this.character);
  }

  delete() {
    this.onDelete.emit(this.formGroup.value);
  }
}
