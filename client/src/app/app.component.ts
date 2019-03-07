import {Component, NgZone, OnInit} from '@angular/core';
import {GlobalModalService} from './global-modal.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private zone: NgZone) {}

  ngOnInit(): void {
    console.log(this.zone);
  }
}
