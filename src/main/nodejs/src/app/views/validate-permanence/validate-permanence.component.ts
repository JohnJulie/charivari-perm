import { Component, OnInit } from '@angular/core';
import { PermanenceModel } from '../../models/permanence.model';
import { PermanenceService } from '../../services/permanence/permanence.service';

import * as moment from 'moment';
import * as _ from 'lodash';

@Component({
  selector: 'app-validate-permanence',
  templateUrl: './validate-permanence.component.html',
  styleUrls: ['./validate-permanence.component.scss']
})
export class ValidatePermanenceComponent implements OnInit {

  public currentPermanence: any;
  public startDate: any;
  public endDate: any;
  public currentDay: string;
  public startHour: string;
  public endHour: string;  
    
  constructor(private permanenceService: PermanenceService) { }

  ngOnInit() {
      this.getCurrentPermanence();
  }

  getCurrentPermanence() {
    this.permanenceService.getCurrentPermanence().subscribe(
      (result: any) => {
          console.log('result:', result);
          this.currentPermanence = result;
          this.startDate = moment(this.currentPermanence.startDate, 'YYYY-MM-DDTHH:mm:ss');
          this.endDate = moment(this.currentPermanence.endDate, 'YYYY-MM-DDTHH:mm:ss');
          console.log('this.currentDate.hours():', this.startDate.hours());
          this.currentDay = this.startDate.format('dddd D MMMM');
          console.log('this.currentDay:', this.currentDay);
          this.startHour = _.toString(this.startDate.hours()) + 'h' + _.toString(this.startDate.minutes());
          this.endHour = _.toString(this.endDate.hours()) + 'h' + _.toString(this.endDate.minutes());
          console.log('this.currentDate:', _.toString(this.startDate.hour()) + 'h' + _.toString(this.startDate.minute()));
      }, (error) => console.log('getCurrentPermanence error:', error)
    )
  }

}
