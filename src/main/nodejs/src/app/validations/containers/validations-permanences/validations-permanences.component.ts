import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PermanenceService } from '../../../shared/services/permanence/permanence.service';
import { PermanenceModel } from '../../../shared/models/permanence.model';

import * as moment from 'moment';
import * as _ from 'lodash';
import { PermanenceStatus } from '../../../shared/models/permanence-status.model';

@Component({
  selector: 'app-validations-permanences',
  templateUrl: './validations-permanences.component.html',
  styleUrls: ['./validations-permanences.component.scss']
})
export class ValidationsPermanencesComponent implements OnInit {

  public currentPermanences: PermanenceModel[];
  public showCurrentButton: boolean;
  public permanenceId: string;

  constructor(
    private permanenceService: PermanenceService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.showCurrentButton = true;
    if (this.activatedRoute.snapshot.paramMap.get('id')) {
      this.permanenceId = this.activatedRoute.snapshot.paramMap.get('id');
      this.permanenceService.getPermanence(this.permanenceId).subscribe(
        (result: PermanenceModel) => {
          this.currentPermanences = [result];

        }, (error) => console.log('getCurrentPermanence error:', error)
      );
    }
  }

  public onCheckPermanence(event) {
    const permanence: PermanenceModel = event;
    if (moment(permanence.endDate).isSameOrBefore(moment()) ||
      (moment(permanence.startDate).isSameOrBefore(moment()) && moment(permanence.endDate).isSameOrAfter(moment()))) {
      this.permanenceService.updatePermanence(permanence).subscribe(
        () => {
          _.find(this.currentPermanences, ['id', permanence.id]).status = permanence.status;
          this.router.navigate(['/validations']);
        }
      );
    }
  }

  public onChooseReplacement(event) {
    const perm = event;
    this.router.navigate(['/validations/' + perm.id + '/replacement']);
  }

}
