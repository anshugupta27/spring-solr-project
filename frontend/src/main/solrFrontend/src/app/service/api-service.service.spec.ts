import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { Email } from '../email';
import {HttpClientTestingModule,HttpTestingController} from '@angular/common/http/testing';
import { ApiServiceService } from './api-service.service';

describe('ApiServiceService', () => {
  let service: ApiServiceService;
  let httpMock:HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule],
      providers:[ApiServiceService]
    });
    service = TestBed.inject(ApiServiceService);
    httpMock = TestBed.get(HttpTestingController);
  });


  let mockResponse:Email[] = [
    {id: "123",
      body: "this is the body of the mail",
       subject: "Update on review work on feeds",
      from: "Mike Jordan",
      date: "03-10-2000",
      to: "Sally Beck, Fernley Dyson",
      cc: "as",
      contentType: "text/plain; charset=us-ascii",
      mimeVersion: 1.0,
      messageId: "<32834468.1075855874221.JavaMail.evans@thyme>" 
    }
   
  ];

  // test 1
  it('should be created', () => {
    service.getAllDocuments(1000,0).subscribe(posts =>{
      expect(posts).toEqual(mockResponse);
 
     });
   
     const request = httpMock.expectOne(`${service.backendRoute}/getAll`);
     expect(request.request.method).toBe('GET');
     request.flush(mockResponse);

  });
 
    afterEach(()=>{
      httpMock.verify();
    });

});






 

 
    
   
 



