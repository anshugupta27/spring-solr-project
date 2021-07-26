import { Component, OnInit } from '@angular/core';
import { ApiServiceService } from 'src/app/service/api-service.service';
import {HttpClient, HttpClientModule, HttpHeaders} from '@angular/common/http' ;
import {Email} from 'src/app/email' ;
import {NgbDate, NgbCalendar, NgbDateParserFormatter, NgbCalendarPersian, NgbInputDatepickerConfig} from '@ng-bootstrap/ng-bootstrap';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { FormBuilder } from '@angular/forms';
import { Placeholder } from '@angular/compiler/src/i18n/i18n_ast';
import { TooltipComponent } from 'ag-grid-community/dist/lib/components/framework/componentTypes';
import { DatePickerComponent } from '../date-picker/date-picker.component';
import { MessageIdFilterComponent } from '../message-id-filter/message-id-filter.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  constructor(private apiService:ApiServiceService,
    private httpClient: HttpClient,
    private modalService: NgbModal,
    private fb: FormBuilder,
    private calendar: NgbCalendar,
    public formatter: NgbDateParserFormatter,
    public config: NgbInputDatepickerConfig) 
    { 
       // date
    this.fromDate = null;
    this.toDate = null;
    config.placement = ['top-left', 'bottom-right'];
    }


// modal
openLg(content:any) {
  this.modalService.open(content, { size: 'lg' });
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



// multi dropdown
dropdownListMessage :any[]=[];
dropdownListFrom :any[]=[];
dropdownSettingsMessage = {};
dropdownSettingsFrom = {};

  ngOnInit(): void {
    this.getAllDocuments() ;

    this.dropdownListMessage = [];
    this.dropdownListFrom = [];

    this.dropdownSettingsMessage = {
      singleSelection: false,
      idField: 'id',
      textField: 'id',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 1,
      allowSearchFilter: true,
      clearSearchFilter:true
    };
    this.dropdownSettingsFrom = {
      singleSelection: false,
      idField: 'from',
      textField: 'from',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 1,
      allowSearchFilter: true
    };
   

  }
  
  
  public getByMessageIDList:any=[];
  public data : any =[] ; 
  public fromFlag = false;
  public messageFlag = false ;



  //drop down Message ID - selected 
  onItemSelectID(item: any) {
    this.fromFlag = false ; 
    this.messageFlag = true ;
    for ( let i = 0 + (this.pageCount*100) ; i < 100+(this.pageCount*100);i++)
    {
      if ( this.fixedRowData[i].id == item )
      this.getByMessageIDList.push(this.fixedRowData[i]) ;
    }

  }

  // drop down Message ID - DeSelected
  onItemDeSelectID(item:any) {
    for ( let [i , list] of this.getByMessageIDList.entries())
    {
      if ( list.id == item )
      this.getByMessageIDList.splice(i,1);
    }
   
  }

  // drop down Message ID - Select All
  onSelectAllID(items: any) {
    this.getByMessageIDList= this.fixedRowData ; 
      this.messageFlag = true ; 
   
  }
  

   // drop down From - Selected
   public getByFromList:any=[];
   public dataFrom : any =[] ; 

   onItemSelectFrom(item: any) {
    this.fromFlag = true ; 
    this.messageFlag = false ;
     for ( let i = 0 + (this.pageCount*100) ; i < 100+(this.pageCount*100);i++)
     {
       if ( item == this.fixedRowData[i].from)
      this.getByFromList.push(this.fixedRowData[i]) ;
     }
   }

   // drop down From - Select All
   onSelectAllFrom(items: any) {
     this.getByFromList= this.fixedRowData ; 
      this.fromFlag = true ; 
  }

  // drop down From - DeSelected
  onItemDeSelectFrom(item:any) {
    for ( let [i , list] of this.getByFromList.entries())
    {
      if ( list.from == item )
      this.getByFromList.splice(i,1);
    }
  }


  // date range picker
  public isHidden:Boolean = false;
  public pageCount:any;
  public filterApplied: boolean=false;
  hoveredDate: NgbDate | null = null;

  fromDate: NgbDate | null;
  toDate: NgbDate | null;

  // form submit 
filterForm = this.fb.group({
  formMessageID: [''],
  formFrom: [''],
  sentDate: ['']  
 
});


formatDate(sentDate:any) {
  var date: String = ""
    date += (Math.floor(sentDate.day/10) == 0 ? "0" +sentDate.day + "-" : "" + sentDate.day + "-");
    date += (Math.floor(sentDate.month/10) == 0 ? "0" + sentDate.month + "-" : "" + sentDate.month + "-");
    date += sentDate.year;
    return date;
}

onDateSelection(date: NgbDate) {
  
  if (!this.fromDate && !this.toDate) {
    this.fromDate = date;
  } else if (this.fromDate && !this.toDate && date && date.after(this.fromDate)) {
    this.toDate = date;
  } else {
    this.toDate = null;
    this.fromDate = date;
  }

 
  
}

isHovered(date: NgbDate) {
  return this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
}

isInside(date: NgbDate) {
  return this.toDate && date.after(this.fromDate) && date.before(this.toDate);
}

isRange(date: NgbDate) {
  return date.equals(this.fromDate) || (this.toDate && date.equals(this.toDate)) || this.isInside(date) || this.isHovered(date);
}

validateInput(currentValue: NgbDate | null, input: string): NgbDate | null {
  const parsed = this.formatter.parse(input);
  return parsed && this.calendar.isValid(NgbDate.from(parsed)) ? NgbDate.from(parsed) : currentValue;
}

public space :string=" TO " ;


// on Form Submit
onSubmit() {
 
  if(this.fromFlag && this.filterForm.value.formFrom ) {
    this.rowData = this.getByFromList ;
    this.filterForm.value.formFrom="";
  }
  else if ( this.messageFlag && this.filterForm.value.formMessageID ) {
    this.rowData = this.getByMessageIDList ;
   
  }
  else  if (this.fromDate != null && this.toDate != null) {
    let dateList: any=[]
    var fromDate = this.formatDate(this.fromDate);
    var toDate = this.formatDate(this.toDate);

    // server side 
    this.apiService.getByDate(fromDate,toDate).subscribe((res) => {
      this.rowData = res ; 
      
    })
  }
  else
  this.rowData = null ;
  
  this.filterForm.reset;

}

// Reset 
public selectedItemMessage:any ;
public selecedItemFrom:any;
public dateRangePicker:any;
resetButton(){
  this.selectedItemMessage=[] ;
  this.selecedItemFrom=[];
  this.getByFromList=[];
  this.getByMessageIDList=[];
  this.filterForm.reset;
  this.rowData=this.fixedRowData;
  this.dateRangePicker = "" ;
}
 


columnDefs = [
   
  {headerName:'MESSAGE ID',field:'id',
  tooltipField:"id"
  ,sortable :true , filter : true , resizable : false,checkboxSelection:true,width:300},
  {headerName:'FROM',field:'from',headerClass: {textAlign: 'center'}, sortable :true , filter : true , resizable : false,tooltipField:"from",width:250},
  {headerName:'SUBJECT',field:'subject', sortable :true , filter : true , resizable : true,tooltipField:"subject",width:250},
  {headerName: 'DATE', field:'date',sortable :true , filter : true, resizable : true,tooltipField:"date",width:160},
  {headerName:'TO',field:'to',sortable :true , filter : true , resizable : true,tooltipField:"to"},
  {headerName:'CC',field:'cc', sortable :true , filter : true , resizable : true,tooltipField:"cc"},
  {headerName: 'MESSAGE', field:'body',sortable :true , filter : true , resizable : true,tooltipField:"body"},
  {headerName:'CONTENT TYPE',field:'contentType', sortable :true, filter : true , resizable : true,tooltipField:"contentType"},
  {headerName: 'ID', field: 'messageId',sortable :true , filter : true  , resizable : true,tooltipField:"id"},
  {headerName:'MIME VERSION',field:'mimeVersion', sortable :true , filter : true , resizable : true,tooltipField:"mimeVersion"}

];


// Pagination

onPaginationChanged(event:any)
{
  this.pageCount = event.api.selectionController.gridApi.paginationProxy.currentPage;
  let tempSet = new Set()
  this.messageID =[];
  this.dropdownListMessage=[];
  this.dropdownListFrom=[];
  this.fromIDarray=[];
  
  for ( let i = 0 + (this.pageCount*100) ; i < 100+(this.pageCount*100);i++)
  {
    var id = this.fixedRowData[i].id;
    this.messageID.push(id) ;
    tempSet.add(this.fixedRowData[i].from);
  }

  for ( let key of tempSet )
  {
    this.fromIDarray.push(key);
  }
  
  this.dropdownListMessage = this.messageID;
  this.dropdownListFrom = this.fromIDarray;
}



  public rowData:any = [];
  public fixedRowData:any=[];
  public messageID:any[] =[] ;
  public fromIDarray:any=[];
  public uniqueMessage:any = [];
  public uniqueFrom:any = [];

  // get All Documents
  public getAllDocuments(){
  this.apiService.getAllDocuments(5000,0).subscribe((res) => {
    this.rowData = res ; 
    this.fixedRowData = res ; 
  })
}


public emails!: Email[];
public messageBody:any;

// on Row click
public onRowClicked(event:any){
  this.apiService.getDocumentById(event.data.id).subscribe((data:Email[]) => {  
    this.emails = data ;
    this.apiService.setMessage(this.emails) ;

    // cleaning Message body
    this.messageBody=this.cleanMessageBody(data) ;
  })

}

cleanMessageBody(data:any)
{
  const index1 = data[0].body.lastIndexOf("Subject:");
    if ( index1 == -1 )
    this.messageBody = data[0].body;
    else{
     const index2 = data[0].body.indexOf("\n",index1) ;
     this.messageBody = data[0].body.substring(index2) ;
    }
    this.messageBody = this.messageBody.replace(/^\s+|\s+$/g,'');
    return this.messageBody;
}

}
