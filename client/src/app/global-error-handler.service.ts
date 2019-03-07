import {ErrorHandler, Injectable, Injector, NgZone} from '@angular/core';
import {GlobalModalService} from './global-modal.service';
import {GlobalModalComponent} from './global-modal/global-modal.component';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {HttpErrorResponse} from '@angular/common/http';

@Injectable()
export class GlobalErrorHandlerService implements ErrorHandler {

  constructor(
    private zone: NgZone,
    private injector: Injector,
  ) { }

  handleError(error: any): void {
    let content = '';

    if (error instanceof HttpErrorResponse) {
      switch (error.status) {
        case 401:
        case 500:
          content = `
            <div class="col-12">
              <p>訊息: ${error.error.message}</p>
              <p>代碼: ${error.error.code}</p>
            </div>`;
          break;
        default:
          content = '非預期Http錯誤';
      }
    } else {
      throw error;
    }
    this.zone.run(() => this.openErrorAlert(content));

  }

  openErrorAlert(content: string, afterClose?: () => void) {
    this.openModal('錯誤', content, 'error-modal', afterClose, afterClose);
  }

  openModal(title: string, content: string, css: string, afterConfirm: () => void, afterCancel: () => void) {
      let modalRef = this.injector.get(NgbModal).open(GlobalModalComponent, {windowClass: css});
      modalRef.componentInstance.title = title;
      modalRef.componentInstance.content = content;
      modalRef.result.then(
        (result) => {
          if (result && afterConfirm) {afterConfirm(); }
          if (!result && afterCancel) {afterCancel(); }
          modalRef = null; }, () => modalRef = null);
  }
}
