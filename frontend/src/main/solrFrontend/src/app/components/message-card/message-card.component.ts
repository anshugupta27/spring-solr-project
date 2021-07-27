import { Component, OnInit } from '@angular/core';
import { Email } from 'src/app/email';
import { ShareDataService } from 'src/app/service/share-data.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-message-card',
  templateUrl: './message-card.component.html',
  styleUrls: ['./message-card.component.css']
})
export class MessageCardComponent implements OnInit {

  public emailBody: any;
  public emails!: Email[];
  constructor(private shareDataService: ShareDataService, private ngbModal: NgbModal) { }

  ngOnInit(): void {
    this.shareDataService.shareMessage.subscribe((res: any) => {
      this.emails = res;
      this.emailBody = this.emails[0].body;
    })
  }

  // modal
  openLg(content: any) {
    this.ngbModal.open(content, { size: 'lg' });

  }
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

}
