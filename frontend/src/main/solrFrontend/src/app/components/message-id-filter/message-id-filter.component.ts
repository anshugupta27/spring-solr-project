import { HttpClient } from '@angular/common/http';

import { NgbModal, NgbCalendar, NgbDateParserFormatter, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Email } from 'src/app/email';
import { ApiServiceService } from 'src/app/service/api-service.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';



@Component({
  selector: 'app-message-id-filter',
  templateUrl: './message-id-filter.component.html',
  styleUrls: ['./message-id-filter.component.css']
})
export class MessageIdFilterComponent implements OnInit {

  constructor(private apiService:ApiServiceService , private httpClient: HttpClient,
    
    private modalService: NgbModal ,  private fb: FormBuilder,
    private calendar: NgbCalendar, public formatter: NgbDateParserFormatter) {
     
     
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

    // const message = JSON.stringify(item) ;
   
    for ( let i = 0 + (this.pageCount*100) ; i < 100+(this.pageCount*100);i++)
    {
      
      if ( this.fixedRowData[i].id == item )
      this.getByMessageIDList.push(this.fixedRowData[i]) ;
    }

    
   
  }

  // drop down Message ID - DeSelected
  onItemDeSelectID(item:any)
  {
   
    for ( let [i , list] of this.getByMessageIDList.entries())
    {
      if ( list.id == item )
      this.getByMessageIDList.splice(i,1);
    }
   
  }

  // drop down Message ID - Select All
  onSelectAllID(items: any) {
    // this.apiService.getAllDocuments(1000,0).subscribe((res) => {

      this.getByMessageIDList= this.fixedRowData ; 
      this.messageFlag = true ; 
    // })
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
    // this.apiService.getAllDocuments(1000,0).subscribe((res) => {

      this.getByFromList= this.fixedRowData ; 
      this.fromFlag = true ; 
    // })
  }

  // drop down From - DeSelected
  onItemDeSelectFrom(item:any)
  {
    for ( let [i , list] of this.getByFromList.entries())
    {
      if ( list.from == item )
      this.getByFromList.splice(i,1);
    }
  }


  // form submit 
filterForm = this.fb.group({
  formMessageID: [''],
  formFrom: ['']
 
});

pageCount:any;
// on Form Submit
onSubmit() {
 
  this.filterApplied=true;
  if(this.fromFlag && this.filterForm.value.formFrom ){
    this.rowData = this.getByFromList ;
    console.log("from list: " + this.getByFromList);
    this.filterForm.value.formFrom="";
  }
  else if ( this.messageFlag && this.filterForm.value.formMessageID )
  {
   
    console.log(this.getByMessageIDList);
    this.rowData = this.getByMessageIDList ;
    console.log(this.rowData);
 
  }
  else
  this.rowData = null ;
  
  this.filterForm.reset;

}

// Reset 
public selectedItemMessage:any ;
public selecedItemFrom:any;

resetButton(){
  this.selectedItemMessage= [] ;
  this.selecedItemFrom=[];
  this.getByFromList=[];
  this.getByMessageIDList=[];
  this.filterForm.reset;
  this.filterApplied=false ;
  this.getAllDocuments() ;
 
}
 

 
 
 customToolTip = "date+id"
 

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

filterApplied:boolean=false;
onPaginationChanged(event:any)
{
  console.log("page count"+this.pageCount);
console.log("event "+event);
  this.pageCount = event.api.selectionController.gridApi.paginationProxy.currentPage;
  let tempSet = new Set()
  this.messageID =[];
  this.dropdownListMessage=[];
  this.dropdownListFrom=[];
  this.fromIDarray=[];
  
  console.log(this.pageCount);

  for ( let i = 0 + (this.pageCount*100) ; i < 100+(this.pageCount*100);i++)
  {
    var id = this.fixedRowData[i].id;
    this.messageID.push(id) ;
    tempSet.add(this.fixedRowData[i].from);
  }

  this.dropdownListMessage = this.messageID;

  for ( let key of tempSet )
  {
    this.fromIDarray.push(key);
  }
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

// on row click
public onRowClicked(event:any){
  this.apiService.getDocumentById(event.data.id).subscribe((data:Email[]) => {  
    this.emails = data ;

    this.apiService.setMessage(this.emails) ;

    // cleaning message body
    const index1 = data[0].body.lastIndexOf("Subject:");
    if ( index1 == -1 )
    this.messageBody = data[0].body;
    else{
     const index2 = data[0].body.indexOf("\n",index1) ;
     this.messageBody = data[0].body.substring(index2) ;
    }
    this.messageBody = this.messageBody.replace(/^\s+|\s+$/g,'');
    
   
  })
 
}



  

}
