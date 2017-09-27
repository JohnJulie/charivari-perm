import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';

@Component({
  selector: 'app-check-permanence',
  templateUrl: './check-permanence.component.html',
  styleUrls: ['./check-permanence.component.scss']
})
export class CheckPermanenceComponent implements OnInit {
  public actualDate;
  public formatedDate: string;
  constructor() { }

  ngOnInit() {
    this.actualDate = new Date().toISOString();
    console.log('date: ', this.actualDate);
  }

}
