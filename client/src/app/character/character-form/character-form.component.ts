import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Character} from '../character';
import {FormArray, FormBuilder, FormGroup} from '@angular/forms';
import {RaceParamService} from '../../parameter/race/race-param.service';
import {RaceParam} from '../../parameter/race/race-param';
import {SexParam} from '../../parameter/sex/sex-param';
import {SexParamService} from '../../parameter/sex/sex-param.service';
import {StarDiceParam} from '../../parameter/star-dice/star-dice-param';
import {StarDiceParamService} from '../../parameter/star-dice/star-dice-param.service';
import {transition, trigger, useAnimation} from '@angular/animations';
import {fadeIn, fadeOut} from 'ng-animate';
import {JwtPayloadService} from '../../security/jwt-payload.service';

@Component({
  selector: 'app-character-form',
  templateUrl: './character-form.component.html',
  styleUrls: ['./character-form.component.css'],
  animations: [
    trigger('fade', [
      transition('void => *', useAnimation(fadeIn, {params: {timing: 1}})),
      transition('* => void', useAnimation(fadeOut, {params: {timing: 1}}))
    ])
  ]
})
export class CharacterFormComponent implements OnInit, OnChanges {
  @Input()
  character: Character;
  defaultCharacter: Character;
  formGroup: FormGroup;

  @Output()
  onInsert = new EventEmitter<Character>();
  @Output()
  onUpdate = new EventEmitter<Character>();
  @Output()
  onDelete = new EventEmitter<Character>();

  raceParams: Array<RaceParam>;
  sexParams: Array<SexParam>;
  starDiceParams: Array<StarDiceParam>;

  constructor(
    private fb: FormBuilder,
    private raceParamService: RaceParamService,
    private sexParamService: SexParamService,
    private starDiceService: StarDiceParamService,
    private jwtPayloadService: JwtPayloadService
  ) {
    this.formGroup = this.fb.group({
      id: [null],
      data: this.fb.group({
        // basic info
        name: [null],
        race: [null],
        starDice: [null],
        // attribute
        hope: [null],
        brave: [null],
        intelligence: [null],
        love: [null],
        currentHope: [null],
        // skill
        fight: [null],
        adventure: [null],
        ride: [null],
        hunting: [null],
        perception: [null],
        knowledge: [null],
        poem: [null],
        convince: [null],
        pray: [null],
        skills: this.fb.array([]),
        // relation
        relation: this.fb.array([]),
        // item
        items: this.fb.array([]),

        // character info
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
        career: [null],

        // other
        description: [null],
        note: [null],

        // state
        isHide: [false],
        isInfoHide: [false]
      }),
      belongUserId: [null]
    });
    this.defaultCharacter = this.formGroup.value;
  }

  ngOnInit() {
    this.raceParamService.fetch()
      .subscribe((params) => this.raceParams = params);
    this.sexParamService.fetch()
      .subscribe((params) => this.sexParams = params);
    this.starDiceService.fetch()
      .subscribe((params) => this.starDiceParams = params);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if ('character' in changes) {
      const character = changes.character.currentValue;
      this.formGroup.reset(this.defaultCharacter);
      this.clearSkills();
      this.clearRelation();
      this.clearItems();

      if (character.data) {
        if (character.data.skills) {character.data.skills.forEach(skill => this.addSkill()); }
        if (character.data.relation) {character.data.relation.forEach(r => this.addRelation()); }
        if (character.data.items) {character.data.items.forEach(item => this.addItem()); }
      }
      this.formGroup.patchValue(character);
    }
  }

  get skills(): FormArray {
    return this.formGroup.get('data.skills') as FormArray;
  }

  addSkill() {
    this.skills.push(this.fb.group({
      name: [null],
      dice: [null],
      effect: [null],
      description: [null]
    }));
  }

  removeSkill(index: number) {
    this.skills.removeAt(index);
  }

  clearSkills() {
    while (this.skills.length !== 0 ) {
      this.removeSkill(0);
    }
  }

  get relation(): FormArray {
    return this.formGroup.get('data.relation') as FormArray;
  }

  addRelation() {
    this.relation.push(this.fb.group({
      name: [null],
      maxValue: [null],
      currentValue: [null]
    }));
  }

  removeRelation(index: number) {
    this.relation.removeAt(index);
  }

  clearRelation() {
    while (this.relation.length !== 0) {
      this.removeRelation(0);
    }
  }

  get items(): FormArray {
    return this.formGroup.get('data.items') as FormArray;
  }

  addItem() {
    this.items.push(this.fb.group({
      name: [null],
      effort: [null]
    }));
  }

  removeItem(index: number) {
    this.items.removeAt(index);
  }

  clearItems() {
    while (this.items.length !== 0) {
      this.removeItem(0);
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

  get isNew(): boolean {
    return this.character.id == null;
  }

  get isOwner(): boolean {
    return this.jwtPayloadService.user.id === this.character.belongUserId;
  }

  get isInfoHide(): boolean {
    return this.character.data && this.character.data.isInfoHide;
  }
}
