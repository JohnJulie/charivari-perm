import { Component, OnInit } from '@angular/core';
import { PermanenceService } from '../../services/permanence/permanence.service';

import * as moment from 'moment';
import * as _ from 'lodash';

@Component({
  selector: 'app-parameters',
  templateUrl: './parameters.component.html',
  styleUrls: ['./parameters.component.scss']
})
export class ParametersComponent implements OnInit {

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

  public validateMonth() {
    this.permanenceService.validateMonth(this.selectedMonth.format('YYYY-MM-DD')).subscribe(
      (res) => {
        _.remove(this.monthToClose, this.selectedMonth);
        this.selectedMonth = null;
      }
    );
  }

}
