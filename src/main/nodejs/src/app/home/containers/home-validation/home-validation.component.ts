import { Component, OnInit } from '@angular/core';
import { PermanenceService } from '../../../shared/services/permanence/permanence.service';
import { PermanenceModel } from '../../../shared/models/permanence.model';
import { interval } from 'rxjs';

import * as moment from 'moment';
import * as _ from 'lodash';
import { Router } from '@angular/router';
import { PermanenceStatus } from '../../../shared/models/permanence-status.model';

@Component({
  selector: 'app-home-validation',
  templateUrl: './home-validation.component.html',
  styleUrls: ['./home-validation.component.scss']
})
export class HomeValidationComponent implements OnInit {

  public currentPermanence: PermanenceModel;
  public currentPermanences: PermanenceModel[];
  public showCurrentButton: boolean;

  constructor(
    private permanenceService: PermanenceService,
    private router: Router
  ) { }

  ngOnInit() {
    this.showCurrentButton = false;
    this.getCurrentPermanence();
    interval(5000 * 60).subscribe(
      () => {
        this.getCurrentPermanence();
      }
    );
  }

  public getCurrentPermanence() {
    this.permanenceService.getCurrentPermanence().subscribe(
      (result: PermanenceModel[]) => {
        this.currentPermanences = result;
        if (!_.isEmpty(this.currentPermanences)) {
          const currentPermanences = _.orderBy(this.currentPermanences, ['startDate'], ['desc']);
          this.currentPermanence = currentPermanences[0];
        }
      }, (error) => console.log('getCurrentPermanence error:', error)
    );
  }

  public onCheckPermanence(event) {
    const permanence: PermanenceModel = event;
    if (moment(permanence.endDate).isSameOrBefore(moment()) ||
      (moment(permanence.startDate).isSameOrBefore(moment()) && moment(permanence.endDate).isSameOrAfter(moment()))) {
      this.permanenceService.updatePermanence(permanence).subscribe(
        () => {
          _.find(this.currentPermanences, ['id', permanence.id]).status = permanence.status;
        }
      );
    }
  }

  public onChooseReplacement(event) {
    const perm = event;
    this.router.navigate(['/permanence/' + perm.id]);
  }

}
