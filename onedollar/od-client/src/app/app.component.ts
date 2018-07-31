import {Component, OnInit} from '@angular/core';
import {RoomService} from "./room.service";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'od-client';
  ova : string;
  constructor(private roomService : RoomService){
  }

  ngOnInit(): void {
      this.roomService.getTest().subscribe(data => this.ova = data);
  }


}
