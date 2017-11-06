import { Component, OnInit } from '@angular/core';
import { PermanenceService } from '../../services/permanence/permanence.service';
import * as moment from 'moment';
import * as _ from 'lodash';
import { PermanenceModel } from '../../models/permanence.model';

@Component({
  selector: 'app-planning-permanence',
  templateUrl: './planning-permanence.component.html',
  styleUrls: ['./planning-permanence.component.scss']
})
export class PlanningPermanenceComponent implements OnInit {

  public currentWeek: number;
  public currentMonday: string;
  public currentSunday: string;
  public nowWeek: number;
  public permanences: any;
  constructor(
    private permanenceService: PermanenceService
  ) { }

  ngOnInit() {
    this.nowWeek = moment().week();
    this.permanences = [
      { label: 'Lundi', perms: [] },
      { label: 'Mardi', perms: [] },
      { label: 'Mercredi', perms: [] },
      { label: 'Jeudi', perms: [] },
      { label: 'Vendredi', perms: [] }
    ];
    _.each(this.permanences, perm => perm = new Array());
    this.currentWeek = this.nowWeek;
    this.getWeekPermanence(this.currentWeek);
  }

  public getWeekPermanence(whichWeek) {
    this.currentWeek = whichWeek;
    const startDate = moment().weeks(whichWeek).weekday(1).format('YYYY-MM-DD');
    this.currentMonday = moment().weeks(whichWeek).weekday(1).format('DD/MM/YYYY');
    const endDate = moment().weeks(whichWeek).weekday(7).format('YYYY-MM-DD');
    this.currentSunday = moment().weeks(whichWeek).weekday(7).format('DD/MM/YYYY');
    this.permanenceService.getWeekPermanence(startDate, endDate).subscribe(
      (result) => {
        _.each(result,
          (perm) => {
            const index = moment(perm.startDate).day() - 1;
            this.permanences[index].perms.push(perm);
          }
        );
        console.log('this.permanences:', this.permanences);
      }
    );

  }
}
