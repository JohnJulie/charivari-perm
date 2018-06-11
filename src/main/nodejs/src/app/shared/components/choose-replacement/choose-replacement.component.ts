import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FamilyModel } from '../../models/family.model';
import { PermanenceModel } from '../../models/permanence.model';
import * as moment from 'moment';
import * as _ from 'lodash';

@Component({
  selector: 'app-choose-replacement',
  templateUrl: './choose-replacement.component.html',
  styleUrls: ['./choose-replacement.component.scss']
})
export class ChooseReplacementComponent implements OnInit, OnChanges {

  @Input() families: FamilyModel[];
  @Input() permanence: PermanenceModel;
  @Input() nobody: FamilyModel;
  @Output() update: EventEmitter<FamilyModel> = new EventEmitter();
  public startDate: any;
  public endDate: any;

  constructor() {}

  ngOnInit() {
    if (this.permanence) {
      this.setDate();
    }
  }

  ngOnChanges(changes: SimpleChanges) {
    if (this.permanence) {
      this.setDate();
    }
  }

  public setDate() {
    this.startDate = moment(_.toString(this.permanence.startDate), 'x');
    this.endDate = moment(_.toString(this.permanence.endDate), 'x');
  }

  public setFamilyToPermanence(family: FamilyModel) {
    this.update.emit(family);
  }

}
