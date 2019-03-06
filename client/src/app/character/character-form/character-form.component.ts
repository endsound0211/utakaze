import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Character} from '../character';
import {FormBuilder, FormGroup} from '@angular/forms';

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

  constructor(private fb: FormBuilder) {
    this.formGroup = this.fb.group({
      id: [null],
      data: this.fb.group({
        name: ['']
      }),
      belongUserId: [null]
    });
  }

  ngOnInit() {
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
