import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IMyDpOptions } from 'mydatepicker';
import { PermanenceModel } from '../../../shared/models/permanence.model';

import * as moment from 'moment';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-validations',
  templateUrl: './validations.component.html',
  styleUrls: ['./validations.component.scss']
})
export class ValidationsComponent implements OnInit {

  @Input() permanences: any;
  @Input() choosePermanence: Array<any>;
  @Output() update: EventEmitter<PermanenceModel> = new EventEmitter();

  public nobodyId = environment.nobody;

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
  }

  updatePermanence(permanence: PermanenceModel) {
    console.log(permanence)
    this.update.emit(permanence);
  }

}
