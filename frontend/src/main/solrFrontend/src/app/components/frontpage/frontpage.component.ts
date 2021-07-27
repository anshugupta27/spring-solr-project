import { Component, OnInit } from '@angular/core';
import { ApiServiceService } from 'src/app/service/api-service.service';
import {HttpClient, HttpClientModule, HttpHeaders} from '@angular/common/http' ;
import {Email} from 'src/app/email' ;

@Component({
  selector: 'app-frontpage',
  templateUrl: './frontpage.component.html',
  styleUrls: ['./frontpage.component.css']
})
export class FrontpageComponent implements OnInit {

  

  constructor(private apiService:ApiServiceService , private httpClient: HttpClient) { }
  ngOnInit(): void {
  }

}


