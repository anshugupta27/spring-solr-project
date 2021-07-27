import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule, HttpHeaders, HttpParams} from '@angular/common/http' ;
import { Email } from 'src/app/email';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ApiServiceService {

  constructor(private httpClient: HttpClient) { }
   public backendRoute = '/spring-solr-email-0.0.1-SNAPSHOT/api/v1' ;
   public message!:Email[] ;

  setMessage ( emails:any )
  {
    this.message = emails;
  }

  getMessage()
  {
    return this.message ;
  }


  //  get All Documents
  getAllDocuments(limit:number, offset:number){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type' : 'application/json',
      }),
    };
    let params = new HttpParams()
    .set('limit', limit)
    .set('offset', offset);
    return this.httpClient.get(`${this.backendRoute}/getAll`,
    {params: params}
    );
  }


// get By Date
getByDate(fromDate:any,toDate:any)
{
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type' : 'application/json',
    }),
  };
  let params = new HttpParams()
  .set('fromDate', fromDate)
  .set('toDate', toDate);
  return this.httpClient.get(`${this.backendRoute}/getByDateRange`,
  {params: params}
  );
}


  // get by ID
  getDocumentById(id:any): Observable<Email[]> {
    return this.httpClient.get<Email[]>(`${this.backendRoute}/getById/${id}`)
  }



}
