import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { PermanenceModel } from '../../models/permanence.model';
import { Router } from '@angular/router';

import * as _ from 'lodash';
import * as moment from 'moment';
import { StorageService } from '../../services/storage/storage.service';
import { AccountType } from '../../models/account-type.model';
import { PermanenceStatus } from '../../../shared/models/permanence-status.model';

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
  public isAdmin: boolean;

  constructor(
    private router: Router,
    private storageService: StorageService
  ) { }

  ngOnInit() {
    this.isAdmin = this.storageService.read('cpu').type === AccountType.admin;
    this.havePermanence = !_.isEmpty(this.currentPermanences);
    if (this.havePermanence) {
      this.setDate();
    }
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
    if (this.isAdmin) {
      if (permanence.status === PermanenceStatus.done) {
        permanence.status = permanence.originalFamilyId === permanence.family.id ? PermanenceStatus.notConfirmed : PermanenceStatus.replacement;
      } else {
        permanence.status = PermanenceStatus.done;
      }
    } else {
      permanence.status = PermanenceStatus.done;
    }
    this.validate.emit(permanence);
  }

  public chooseReplacement(permanence: PermanenceModel) {
    this.replace.emit(permanence);
  }

  public goToCurrentPerm() {
    this.router.navigate(['/permanence']);
  }
}
