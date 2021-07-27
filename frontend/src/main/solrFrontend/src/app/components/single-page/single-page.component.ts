import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpClientModule, HttpHeaders} from '@angular/common/http' ;
import {Email} from 'src/app/email' ;
import { ApiServiceService } from 'src/app/service/api-service.service';

@Component({
  selector: 'app-single-page',
  templateUrl: './single-page.component.html',
  styleUrls: ['./single-page.component.css']
})

export class SinglePageComponent implements OnInit {

  isReadMorecc = true
  isReadMoreto = true 
  isReadMoretext = true 
  isReadMorecontent = true 

  constructor(private apiService:ApiServiceService , private httpClient: HttpClient) { }

  public emails!: Email[];
  public emailBody:any;

  ngOnInit(): void {

    this.emails = this.apiService.getMessage() ;
    this.emailBody = this.emails[0].body;
    
  }

  
  showcontent(){
    this.isReadMorecontent = !this.isReadMorecontent ;
  }

  showcc(){
    this.isReadMorecc = !this.isReadMorecc ;
  }
  showText() {
    this.isReadMoretext = !this.isReadMoretext ;
    
  }

  showto(){
    this.isReadMoreto = !this.isReadMoreto ;
  }

  
}
