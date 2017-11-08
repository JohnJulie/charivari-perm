import { Component, OnInit } from '@angular/core';
import { PermanenceModel } from '../../models/permanence.model';
import { PermanenceService } from '../../services/permanence/permanence.service';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/switchMap';

import * as moment from 'moment';
import * as _ from 'lodash';
import { ActivatedRoute, Router } from '@angular/router';

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

  constructor(
    private permanenceService: PermanenceService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
      if (this.activatedRoute.snapshot.paramMap.get('id')) {
        this.permanenceId = this.activatedRoute.snapshot.paramMap.get('id');
        this.permanenceService.getPermanence(this.permanenceId).subscribe(
          (result: PermanenceModel) => {

              this.currentPermanence = result;
              this.startDate = moment(_.toString(this.currentPermanence.startDate), 'x');
              this.endDate = moment(_.toString(this.currentPermanence.endDate), 'x');
              this.havePermanence = true;

          }, (error) => console.log('getCurrentPermanence error:', error)
        );
      } else {
        this.permanenceId = null;
        this.getCurrentPermanence();
        Observable.interval(5000 * 60).subscribe(
          () => {
            this.getCurrentPermanence();
          }
        );
      }
  }

  public getCurrentPermanence() {
    console.log('plop');
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
    permanence.status = 'DONE';
    this.permanenceService.updatePermanence(permanence).subscribe(
      () => this.currentPermanence.status = permanence.status
    );
  }
}
