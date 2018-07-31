import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  public data: string;
  private baseUrl = 'http://localhost:8082/room/ip';  // URL to web api
  constructor( private http: HttpClient ) { }

  public getTest() {
    return this.http.get(this.baseUrl, {responseType: 'text'});
  }
/*
  getTest(): Observable<any>{
    return this.http.get(this.baseUrl, {
      params: {
        email: 'kmojanovska@yahoo.com'
      }
    });
  }*/
}
