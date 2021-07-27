import { Component, OnInit } from '@angular/core';
import { ApiServiceService } from 'src/app/service/api-service.service';

import { Email } from 'src/app/email';
import { NgbDate, NgbCalendar, NgbDateParserFormatter, NgbInputDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder } from '@angular/forms';
import { ShareDataService } from 'src/app/service/share-data.service';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {

  pageSize: any = 100;
  public rowData: any = [];
  public fixedRowData: any = [];
  public messageID: any[] = [];
  public fromIDarray: any = [];
  public uniqueMessage: any = [];
  public uniqueFrom: any = [];

  // form submit 
  filterForm = this.formBuilder.group({
    formMessageID: [''],
    formFrom: [''],
    sentDate: ['']
  });

  // multi dropdown
  dropdownSettingsMessage = {};
  dropdownSettingsFrom = {};

  constructor(
    private apiService: ApiServiceService,
    private formBuilder: FormBuilder,
    private calendar: NgbCalendar,
    public formatter: NgbDateParserFormatter,
    public config: NgbInputDatepickerConfig,
    private shareDataService: ShareDataService) {

    // date
    this.fromDate = null;
    this.toDate = null;
    config.placement = ['top-left', 'bottom-right'];
  }


  ngOnInit(): void {
    this.dropdownListMessage = [];
    this.dropdownListFrom = [];

    this.shareDataService.sharePageCount.subscribe((res: any) => {
      this.pageCount = res;
    })
    this.shareDataService.shareDropdownListFrom.subscribe((res: any) => {
      this.dropdownListFrom = res;
    })
    this.shareDataService.shareDropdownListMessage.subscribe((res: any) => {
      this.dropdownListMessage = res;
    })
    this.shareDataService.sharefixedRowData.subscribe((res: any) => {
      this.fixedRowData = res;
    })

    this.dropdownSettingsMessage = {
      singleSelection: false,
      idField: 'id',
      textField: 'id',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 1,
      allowSearchFilter: true,
      clearSearchFilter: true
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


  // ********************************************messageID dropDown******************************************
  public getByMessageIDList: any = [];
  dropdownListMessage: any[] = [];
  public messageFlag = false;

  //drop down Message ID - selected 
  onItemSelectID(item: any) {
    this.fromFlag = false;
    this.messageFlag = true;
    for (let i = 0 + (this.pageCount * this.pageSize); i < this.pageSize + (this.pageCount * this.pageSize); i++) {
      if (this.fixedRowData[i].id == item)
        this.getByMessageIDList.push(this.fixedRowData[i]);
    }

  }

  // drop down Message ID - DeSelected
  onItemDeSelectID(item: any) {
    for (let [i, list] of this.getByMessageIDList.entries()) {
      if (list.id == item)
        this.getByMessageIDList.splice(i, 1);
    }

  }

  // drop down Message ID - Select All
  onSelectAllID(items: any) {
    for (let i = 0 + (this.pageCount * this.pageSize); i < this.pageSize + (this.pageCount * this.pageSize); i++) {

      this.getByMessageIDList.push(this.fixedRowData[i]);
    }
    this.messageFlag = true;

  }



  // ********************************************From dropDown***************************************************
  public getByFromList: any = [];
  dropdownListFrom: any[] = [];
  public fromFlag = false;

  onItemSelectFrom(item: any) {
    this.fromFlag = true;
    this.messageFlag = false;
    for (let i = 0 + (this.pageCount * this.pageSize); i < this.pageSize + (this.pageCount * this.pageSize); i++) {
      if (item == this.fixedRowData[i].from)
        this.getByFromList.push(this.fixedRowData[i]);
    }
  }

  // drop down From - Select All
  onSelectAllFrom(items: any) {
    for (let i = 0 + (this.pageCount * this.pageSize); i < this.pageSize + (this.pageCount * this.pageSize); i++) {

      this.getByFromList.push(this.fixedRowData[i]);
    }
    this.fromFlag = true;
  }


  // ********************************************Date dropDown***************************************************
  public isHidden: Boolean = false;
  public pageCount: any;
  public filterApplied: boolean = false;
  hoveredDate: NgbDate | null = null;
  public dateDropDownLabel: string = "";
  fromDate: NgbDate | null;
  toDate: NgbDate | null;


  formatDate(sentDate: any) {
    var date: String = ""
    date += (Math.floor(sentDate.day / 10) == 0 ? "0" + sentDate.day + "-" : "" + sentDate.day + "-");
    date += (Math.floor(sentDate.month / 10) == 0 ? "0" + sentDate.month + "-" : "" + sentDate.month + "-");
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
    this.dateDropDownLabel = "";
    this.dateDropDownLabel = this.formatter.format(this.fromDate) + " TO " + this.formatter.format(this.toDate);

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


  //********************************************Submit Button***************************************************

  onSubmit() {

    if (this.fromFlag && this.filterForm.value.formFrom) {
      this.rowData = this.getByFromList;
      this.shareDataService.shareRowData.next(this.rowData);
    }


    else if (this.messageFlag && this.filterForm.value.formMessageID) {
      this.rowData = this.getByMessageIDList;
      this.shareDataService.shareRowData.next(this.rowData);
    }

    else if (this.fromDate != null && this.toDate != null) {
      {
        var fromDate = this.formatDate(this.fromDate);
        var toDate = this.formatDate(this.toDate);
      }

      this.apiService.getByDate(fromDate, toDate).subscribe((res) => {
        this.rowData = res;
        console.log(this.rowData);
        this.shareDataService.shareRowData.next(this.rowData);
      })
    }
    else {
      this.rowData = null;
      this.shareDataService.shareRowData.next(this.rowData);
    }

    this.filterForm.reset;


  }

  //********************************************Reset Button***************************************************
  public selectedItemMessage: any;
  public selecedItemFrom: any;


  resetButton() {
    this.selectedItemMessage = [];
    this.selecedItemFrom = [];
    this.getByFromList = [];
    this.getByMessageIDList = [];
    this.filterForm.reset;
    this.dateDropDownLabel = "Date";
    this.rowData = this.fixedRowData;
    this.shareDataService.shareRowData.next(this.rowData);

  }

  
}
