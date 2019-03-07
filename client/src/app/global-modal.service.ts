import { Injectable } from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {GlobalModalComponent} from './global-modal/global-modal.component';

@Injectable()
export class GlobalModalService {

  constructor(private modalService: NgbModal) { }

  openErrorAlert(content: string, afterClose?: () => void) {
    this.openModal('錯誤', content, 'error-modal', afterClose, afterClose);
  }

  openInfoAlert(content: string, afterClose?: () => void) {
    this.openModal('訊息', content, 'info-modal', afterClose, afterClose);
  }

  openConffirmAlert(content: string, afterConfirm?: () => void, afterCancel?: () => void) {
    this.openModal('確認', content, 'info-modal', afterConfirm, afterCancel);
  }

  openModal(title: string, content: string, css: string, afterConfirm: () => void, afterCancel: () => void) {
    let modalRef = this.modalService.open(GlobalModalComponent, {windowClass: css});
    modalRef.componentInstance.title = title;
    modalRef.componentInstance.content = content;
    modalRef.result.then(
      (result) => {
        if (result && afterConfirm) {afterConfirm(); }
        if (!result && afterCancel) {afterCancel(); }
        modalRef = null; }, () => modalRef = null);
  }
}
