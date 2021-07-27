import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShareDataService {

  shareMessage = new Subject();
  sharePageCount = new Subject();
  shareDropdownListMessage = new Subject() ;
  shareDropdownListFrom = new Subject() ;
  shareRowData = new Subject() ;
  sharefixedRowData = new Subject() ;
  constructor() { }

}
