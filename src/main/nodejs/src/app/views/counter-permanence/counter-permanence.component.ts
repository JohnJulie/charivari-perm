import { Component, OnInit } from '@angular/core';
import { PermanenceService } from '../../services/permanence/permanence.service';
import { PermanenceModel } from '../../models/permanence.model';
import * as moment from 'moment';
import * as _ from 'lodash';

@Component({
  selector: 'app-counter-permanence',
  templateUrl: './counter-permanence.component.html',
  styleUrls: ['./counter-permanence.component.scss']
})
export class CounterPermanenceComponent implements OnInit {

  public monthToClose;
  public selectedMonth;

  constructor(
    private permanenceService: PermanenceService
  ) { }

  ngOnInit() {
    this.permanenceService.getMonthToValidate().subscribe(
      months => {
        this.monthToClose = [];
        const sortedMonth = _.orderBy(months, ['year', 'monthValue'], ['asc', 'asc']);
        _.forEach(sortedMonth, month => {
          this.monthToClose.push(moment({year: month.year, months: month.monthValue - 1, day: month.dayOfMonth}));
        });
      }
    );
  }

}
