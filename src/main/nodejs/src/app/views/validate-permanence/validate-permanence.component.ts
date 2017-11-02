import { Component, OnInit } from '@angular/core';
import { PermanenceModel } from '../../models/permanence.model';
import { PermanenceService } from '../../services/permanence/permanence.service';

import * as moment from 'moment';
import * as _ from 'lodash';
import { Router } from '@angular/router';

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

  constructor(
    private permanenceService: PermanenceService,
    private router: Router
  ) { }

  ngOnInit() {
      this.getCurrentPermanence();
  }

  getCurrentPermanence() {
    this.permanenceService.getCurrentPermanence().subscribe(
      (result: PermanenceModel) => {
          console.log('result:', result);
          this.currentPermanence = result;
        // const startResult = "1509354000000";
        // const endResult = "1509364800000";
        // console.log('start', moment(startResult, 'x'));
        // console.log('end', moment(endResult, 'x'));
        // this.startDate = moment(startResult, 'x');
        // this.endDate = moment(endResult, 'x');
        //
        // this.startHour = _.toString(this.startDate.hours()) + 'h' + _.toString(this.startDate.minutes());
        // this.endHour = _.toString(this.endDate.hours()) + 'h' + _.toString(this.endDate.minutes());
        this.currentPermanence.id = 5;
          if (!_.isEmpty(this.currentPermanence)) {
            this.havePermanence = true;
            this.startDate = moment(_.toString(this.currentPermanence.startDate), 'x');
            this.endDate = moment(_.toString(this.currentPermanence.endDate), 'x');
          } else {
            this.havePermanence = false;
          }
      }, (error) => console.log('getCurrentPermanence error:', error)
    );
  }
}
