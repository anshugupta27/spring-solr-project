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

  constructor(private apiService:ApiServiceService , private httpClient: HttpClient) { }

  public emails!: Email[];
  public messageBody:any;

  ngOnInit(): void {

    this.emails = this.apiService.getMessage() ;
    
    // cleaning the text
    const index1 = this.emails[0].body.lastIndexOf("Subject:");
    if ( index1 == -1 )
    this.messageBody = this.emails[0].body;
    else{
     const index2 = this.emails[0].body.indexOf("\n",index1) ;
     this.messageBody = this.emails[0].body.substring(index2) ;
    }
    this.messageBody = this.messageBody.replace(/^\s+|\s+$/g,'');
    
  }

  
  isReadMorecc = true
  isReadMoreto = true 
  isReadMoretext = true 
  isReadMorecontent = true 

 

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
