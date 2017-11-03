import { Component, OnInit } from '@angular/core';
import { PermanenceModel } from '../../models/permanence.model';
import { PermanenceService } from '../../services/permanence/permanence.service';
import 'rxjs/add/operator/switchMap';

import * as moment from 'moment';
import * as _ from 'lodash';
import { Router } from '@angular/router';

@Component({
  selector: 'app-validate-permanence',
  templateUrl: './validate-permanence.component.html',
  styleUrls: ['./validate-permanence.component.scss']
})
export class ValidatePermanenceComponent implements OnInit {

  public currentPermanence: Array<PermanenceModel>;
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

  public getCurrentPermanence() {
    this.permanenceService.getCurrentPermanence().subscribe(
      (result: Array<PermanenceModel>) => {
          console.log('result:', result);
          this.currentPermanence = result;
          if (!_.isEmpty(this.currentPermanence)) {
            this.startDate = moment(_.toString(this.currentPermanence[0].startDate), 'x');
            this.endDate = moment(_.toString(this.currentPermanence[0].endDate), 'x');
            this.havePermanence = true;
          } else {
            this.havePermanence = false;
          }
      }, (error) => console.log('getCurrentPermanence error:', error)
    );
  }

  public setPermanceToCheck(permanence: PermanenceModel, index: number) {
    permanence.status = 'DONE';
    this.permanenceService.updatePermanence(permanence).subscribe(
      () => this.currentPermanence[index].status = permanence.status
    );
  }
}
