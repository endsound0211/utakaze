import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-global-modal',
  templateUrl: './global-modal.component.html',
  styleUrls: ['./global-modal.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class GlobalModalComponent implements OnInit {
  @Input()
  title: string;
  @Input()
  content: string;

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit() {
  }

}
