import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import * as moment from 'moment';
import { IMyDpOptions } from 'mydatepicker';
import { PermanenceModel } from '../../../shared/models/permanence.model';

@Component({
  selector: 'app-replacements',
  templateUrl: './replacements.component.html',
  styleUrls: ['./replacements.component.scss']
})
export class ReplacementsComponent implements OnInit {

  @Input() replacements: any;
  @Input() choosePermanence: Array<any>;
  @Output() search: EventEmitter<PermanenceModel> = new EventEmitter();
  @Output() update: EventEmitter<PermanenceModel> = new EventEmitter();

  public myDatePickerOptions: IMyDpOptions;
  public replacementForm: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.myDatePickerOptions = {
      // other options...
      dateFormat: 'dd/mm/yyyy',
      openSelectorTopOfInput: true,
      satHighlight: true,
      disableWeekends: true,
      showWeekNumbers: true,
      editableDateField: false,
      disableUntil: {year: moment().year(), month: moment().month() + 1, day: moment().date() - 1}
    };
    this.replacementForm = this.fb.group({
      date: [null, Validators.required],
      slot: [null, Validators.required]
    });
  }

  searchPermanence() {
    const dateFormDatepicker = this.replacementForm.value.date;
    const date = moment(dateFormDatepicker.formatted, 'DD/MM/YYYY').format('YYYY-MM-DD');
    this.search.emit([date, this.replacementForm.value.slot]);
  }

  updatePermanence(permanence: PermanenceModel) {
    this.update.emit(permanence);
  }

}
