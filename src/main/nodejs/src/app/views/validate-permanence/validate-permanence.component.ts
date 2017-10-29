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

  public currentPermanence: PermanenceModel;
  public havePermanence: boolean;
  public startDate: any;
  public endDate: any;
  public startHour: string;
  public endHour: string;

  constructor(private permanenceService: PermanenceService) { }

  ngOnInit() {
      this.getCurrentPermanence();
  }

  getCurrentPermanence() {
    this.permanenceService.getCurrentPermanence().subscribe(
      (result: PermanenceModel) => {
          console.log('result:', result);
          this.currentPermanence = result;
          if (!_.isEmpty(this.currentPermanence)) {
            this.havePermanence = true;
            this.startDate = moment(this.currentPermanence.startDate, 'YYYY-MM-DDTHH:mm:ss');
            this.endDate = moment(this.currentPermanence.endDate, 'YYYY-MM-DDTHH:mm:ss');
            this.startHour = _.toString(this.startDate.hours()) + 'h' + _.toString(this.startDate.minutes());
            this.endHour = _.toString(this.endDate.hours()) + 'h' + _.toString(this.endDate.minutes());
          } else {
            this.havePermanence = false;
          }
      }, (error) => console.log('getCurrentPermanence error:', error)
    )
  }

}
