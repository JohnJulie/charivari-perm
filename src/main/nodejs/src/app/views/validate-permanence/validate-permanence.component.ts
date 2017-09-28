import { Component, OnInit } from '@angular/core';
import { PermanenceModel } from '../../models/permanence.model';

@Component({
  selector: 'app-validate-permanence',
  templateUrl: './validate-permanence.component.html',
  styleUrls: ['./validate-permanence.component.scss']
})
export class ValidatePermanenceComponent implements OnInit {

  public currentPermanence: PermanenceModel;
  constructor() { }

  ngOnInit() {
  }

  getCurrentPermanence() {

  }

}
