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

});






 

 
    
   
 



