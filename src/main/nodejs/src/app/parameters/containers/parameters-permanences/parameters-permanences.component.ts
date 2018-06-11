import { Component, OnInit } from '@angular/core';
import { PermanenceService } from '../../../shared/services/permanence/permanence.service';

import * as moment from 'moment';
import * as _ from 'lodash';

@Component({
  selector: 'app-parameters-permanences',
  templateUrl: './parameters-permanences.component.html',
  styleUrls: ['./parameters-permanences.component.scss']
})
export class ParametersPermanencesComponent implements OnInit {

  public monthToClose;

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

  public onCloseMonth(month) {
    this.permanenceService.validateMonth(month.format('YYYY-MM-DD')).subscribe(
      (res) => {
        _.remove(this.monthToClose, month);
      }
    );
  }

}
