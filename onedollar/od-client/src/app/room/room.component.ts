import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {
  email : string;
  phone : string;
  error : string;
  phoneForm = true;
  emailForm = false;
  paymentForm = false;
  userForm = false;
  room: number;
  generationTime: Date;
  noPeople: string;
  noPayingCustomers: string;
  constructor( private http: HttpClient ) { }

  ngOnInit() {

  }
  onSubmit() {
    this.http.get('http://localhost:8082/roomp/'+this.phone+'/'+this.email, {responseType: 'text'})
      .subscribe(it => {console.log(it); if(it.slice(2,3)=='A') { this.error = it.slice(2,it.length-4);} else { this.generationTime=new Date(); this.emailForm=false; this.paymentForm=true; }} );
  }

  onSubmitFirst(){
    this.emailForm = true;
    this.phoneForm = false;
  }
  getRoom(){
    this.http.get('http://localhost:8082/findroom/'+this.phone, {responseType: 'text'})
      .subscribe(it=> { this.room = +it; this.method(); });
  }

  method(){
    this.http.get('http://localhost:8082/room/CountPresentPeoplePerGivenRoom/'+this.room, {responseType: 'text'})
      .subscribe(it=> { this.noPeople = it });
    this.http.get('http://localhost:8082/room/CountPayingCustomersPerGivenRoom/'+this.room, {responseType: 'text'})
      .subscribe(it=> { this.noPayingCustomers = it });
  }

  chargeCreditCard(){
    this.paymentForm = false;
    this.userForm = true;
    this.getRoom();


  }

}
