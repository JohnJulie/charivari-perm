
import {interval as observableInterval} from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { PermanenceModel } from '../../shared/models/permanence.model';
import { PermanenceService } from '../../shared/services/permanence/permanence.service';
import { Observable } from 'rxjs/Rx';


import * as moment from 'moment';
import * as _ from 'lodash';
import { ActivatedRoute, Router } from '@angular/router';
import { CurrentStateService } from '../../services/shared-data/current-state.service';

@Component({
  selector: 'app-validate-permanence',
  templateUrl: './validate-permanence.component.html',
  styleUrls: ['./validate-permanence.component.scss']
})
export class ValidatePermanenceComponent implements OnInit {

  public permanenceId: string;
  public currentPermanence: PermanenceModel;
  private currentPermanences: Array<PermanenceModel>;
  public havePermanence: boolean;
  public startDate: any;
  public endDate: any;
  public startHour: string;
  public endHour: string;
  public previousUrl: string;
  public showCurrentButton: boolean;

  constructor(
    private permanenceService: PermanenceService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private currentStateService: CurrentStateService
  ) { }

  ngOnInit() {
    this.currentStateService.state.subscribe(
      (state => {
        this.previousUrl = state;
        this.showCurrentButton = !_.isEmpty(this.previousUrl) && this.previousUrl !== 'permanence';
        console.log('this.previousUrl:', this.previousUrl, _.isEmpty(this.previousUrl), this.showCurrentButton);
      })
    );
    if (this.activatedRoute.snapshot.paramMap.get('id')) {
      this.permanenceId = this.activatedRoute.snapshot.paramMap.get('id');
      this.permanenceService.getPermanence(this.permanenceId).subscribe(
        (result: PermanenceModel) => {
            this.currentPermanences = [result];
            this.startDate = moment(_.toString(this.currentPermanences[0].startDate), 'x');
            this.endDate = moment(_.toString(this.currentPermanences[0].endDate), 'x');
            this.havePermanence = true;

        }, (error) => console.log('getCurrentPermanence error:', error)
      );
    } else {
      this.permanenceId = null;
      this.getCurrentPermanence();
      observableInterval(5000 * 60).subscribe(
        () => {
          this.getCurrentPermanence();
        }
      );
    }
  }

  public getCurrentPermanence() {
    this.permanenceService.getCurrentPermanence().subscribe(
      (result: Array<PermanenceModel>) => {
          console.log('result:', result);
          this.currentPermanences = result;
          if (!_.isEmpty(this.currentPermanences)) {
            const currentPermanences = _.orderBy(this.currentPermanences, ['startDate'], ['desc']);
            this.currentPermanence = currentPermanences[0];
            this.startDate = moment(_.toString(this.currentPermanence.startDate), 'x');
            this.endDate = moment(_.toString(this.currentPermanence.endDate), 'x');
            this.havePermanence = true;
          } else {
            this.havePermanence = false;
          }
      }, (error) => console.log('getCurrentPermanence error:', error)
    );
  }

  public setPermanceToCheck(permanence: PermanenceModel) {
    if (moment(permanence.endDate).isSameOrBefore(moment()) ||
      (moment(permanence.startDate).isSameOrBefore(moment()) && moment(permanence.endDate).isSameOrAfter(moment()))) {
      permanence.status = 'DONE';
      this.permanenceService.updatePermanence(permanence).subscribe(
        () => {
          _.find(this.currentPermanences, ['id', permanence.id]).status = 'DONE';
          this.router.navigate([this.previousUrl]);
        }
      );
    }
  }
}
