import { Component, OnInit } from '@angular/core';
import { ApiServiceService } from 'src/app/service/api-service.service';
import { Email } from 'src/app/email';
import { NgbDate, NgbCalendar, NgbDateParserFormatter, NgbInputDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder } from '@angular/forms';
import { ShareDataService } from 'src/app/service/share-data.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

  pageSize :any = 100;
  pageCount: any;
  
 
  

  constructor(
    private apiService: ApiServiceService,
    private formBuilder: FormBuilder,
    private calendar: NgbCalendar,
    public formatter: NgbDateParserFormatter,
    public config: NgbInputDatepickerConfig,
    private shareDataService: ShareDataService) {

     
  }


  dropdownListMessage :any[]=[];
  dropdownListFrom :any[]=[];
  ngOnInit(): void {
    this.getAllDocuments();

    this.dropdownListMessage = [];
    this.dropdownListFrom = [];

    this.shareDataService.shareRowData.subscribe((res:any)=>{
      this.rowData = res ;
    })





  }


// ********************************************AG Grid***************************************************

  columnDefs = [

    {headerName: 'MESSAGE ID', field: 'id',tooltipField: "id",sortable: true, filter: true, resizable: false, checkboxSelection: true, width: 300},
    { headerName: 'FROM', field: 'from', headerClass: { textAlign: 'center' }, sortable: true, filter: true, resizable: false, tooltipField: "from", width: 250 },
    { headerName: 'SUBJECT', field: 'subject', sortable: true, filter: true, resizable: true, tooltipField: "subject", width: 250 },
    { headerName: 'DATE', field: 'date', sortable: true, filter: true, resizable: true, tooltipField: "date", width: 160 },
    { headerName: 'TO', field: 'to', sortable: true, filter: true, resizable: true, tooltipField: "to" },
    { headerName: 'CC', field: 'cc', sortable: true, filter: true, resizable: true, tooltipField: "cc" },
    { headerName: 'MESSAGE', field: 'body', sortable: true, filter: true, resizable: true, tooltipField: "body" },
    { headerName: 'CONTENT TYPE', field: 'contentType', sortable: true, filter: true, resizable: true, tooltipField: "contentType" },
    { headerName: 'ID', field: 'messageId', sortable: true, filter: true, resizable: true, tooltipField: "id" },
    { headerName: 'MIME VERSION', field: 'mimeVersion', sortable: true, filter: true, resizable: true, tooltipField: "mimeVersion" }

  ];










//********************************************Pagination***************************************************
public rowData: any = [];
public fixedRowData: any = [];
public messageID: any[] = [];
public fromIDarray: any = [];
public uniqueMessage: any = [];
public uniqueFrom: any = [];

onPaginationChanged(event: any) {
    this.pageCount = event.api.selectionController.gridApi.paginationProxy.currentPage;
    this.shareDataService.sharePageCount.next(this.pageCount);
    let tempSet = new Set()
    this.dropdownListMessage = [];
    this.dropdownListFrom = [];
    this.fromIDarray = [];

    for (let i = 0 + (this.pageCount * this.pageSize); i < this.pageSize + (this.pageCount * this.pageSize); i++) {
      var id = this.fixedRowData[i].id;
      this.dropdownListMessage.push(id);
      tempSet.add(this.fixedRowData[i].from);
    }

    for (let key of tempSet) {
      this.fromIDarray.push(key);
    }

    
    this.dropdownListFrom = this.fromIDarray;

    this.shareDataService.shareDropdownListMessage.next(this.dropdownListMessage);
    this.shareDataService.shareDropdownListFrom.next(this.dropdownListFrom);
  }



 //********************************************Get All Documents************************************************
 
  public getAllDocuments() {
    this.apiService.getAllDocuments(5000, 0).subscribe((res) => {
      this.rowData = res;
      this.fixedRowData = res;
      this.shareDataService.sharefixedRowData.next(this.fixedRowData);
    })
  }


//********************************************on Row Click***************************************************
  public emails!: Email[];
  public messageBody: any;

  // on Row click
  public onRowClicked(event: any) {
    this.apiService.getDocumentById(event.data.id).subscribe((data: Email[]) => {
      this.emails = data;
      this.messageBody = this.formatBody(this.emails);
      this.emails[0].body = this.messageBody;
      this.apiService.setMessage(this.emails);
      this.shareDataService.shareMessage.next(this.emails);
    })

  }

//********************************************format Message***************************************************
  formatBody(data: any) {
    const index1 = data[0].body.lastIndexOf("Subject:");
    if (index1 == -1)
      this.messageBody = data[0].body;
    else {
      const index2 = data[0].body.indexOf("\n", index1);
      this.messageBody = data[0].body.substring(index2);
    }
    this.messageBody = this.messageBody.replace(/^\s+|\s+$/g, '');
    return this.messageBody;
  }

}
