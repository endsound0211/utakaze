import {Component, OnDestroy, OnInit} from '@angular/core';
import {SocketService} from './socket.service';

@Component({
  selector: 'app-backend',
  templateUrl: './backend.component.html',
  styleUrls: ['./backend.component.css']
})
export class BackendComponent implements OnInit, OnDestroy {
  connected = false;

  constructor(private socketService: SocketService) { }

  ngOnInit() {
    this.socketService.connect();
    this.socketService.connected$
      .subscribe((connected) => this.connected = connected);
  }

  ngOnDestroy(): void {
    this.socketService.disconnect();
  }
}
