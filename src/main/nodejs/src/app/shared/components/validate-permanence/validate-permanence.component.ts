import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { PermanenceModel } from '../../models/permanence.model';
import { Router } from '@angular/router';

import * as _ from 'lodash';
import * as moment from 'moment';

@Component({
  selector: 'app-validate-permanence',
  templateUrl: './validate-permanence.component.html',
  styleUrls: ['./validate-permanence.component.scss']
})
export class ValidatePermanenceComponent implements OnInit, OnChanges {

  @Input() currentPermanences: PermanenceModel[];
  @Input() showCurrentButton: boolean;
  @Output() validate: EventEmitter<PermanenceModel> = new EventEmitter();
  @Output() replace: EventEmitter<PermanenceModel> = new EventEmitter();

  public startDate: any;
  public endDate: any;
  public havePermanence: boolean;

  constructor(private router: Router) { }

  ngOnInit() {
    this.havePermanence = !_.isEmpty(this.currentPermanences);
    console.log('currentPermanences:', this.currentPermanences);
  }

  ngOnChanges() {
    if (!_.isEmpty(this.currentPermanences)) {
      this.havePermanence = true;
      this.setDate();
    }
  }

  public setDate() {
    this.startDate = moment(_.toString(this.currentPermanences[0].startDate), 'x');
    this.endDate = moment(_.toString(this.currentPermanences[0].endDate), 'x');
  }

  public checkPermanence(permanence: PermanenceModel) {
    this.validate.emit(permanence);
  }

  public chooseReplacement(permanence: PermanenceModel) {
    this.replace.emit(permanence);
  }

  public goToCurrentPerm() {
    this.router.navigate(['/permanence']);
  }
}
