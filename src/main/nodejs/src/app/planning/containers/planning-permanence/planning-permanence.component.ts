import { Component, OnInit } from '@angular/core';
import { PermanenceService } from '../../../shared/services/permanence/permanence.service';
import * as moment from 'moment';
import * as _ from 'lodash';
import { PermanenceModel } from '../../../shared/models/permanence.model';
import { ActivatedRoute, Router } from '@angular/router';

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
  public permanenceId: string;

  constructor(
    private permanenceService: PermanenceService,
    private router: Router
  ) { }

  ngOnInit() {

    this.nowWeek = moment().week();
    _.each(this.permanences, perm => perm = new Array());
    this.currentWeek = this.nowWeek;
    this.getWeekPermanence(this.currentWeek);
  }

  public reinitPermanences() {
    this.permanences = [
      { label: 'Lundi', perms: [{label: 'De 7h45 à 10h45', families: []}, {label: 'De 10h à 13h', families: []}, {label: 'De 15h30 à 18h30', families: []}] },
      { label: 'Mardi', perms: [{label: 'De 7h45 à 10h45', families: []}, {label: 'De 10h à 13h', families: []}, {label: 'De 15h30 à 18h30', families: []}] },
      { label: 'Mercredi', perms: [{label: 'De 7h45 à 10h45', families: []}, {label: 'De 10h à 13h', families: []}, {label: 'De 15h30 à 18h30', families: []}] },
      { label: 'Jeudi', perms: [{label: 'De 7h45 à 10h45', families: []}, {label: 'De 10h à 13h', families: []}, {label: 'De 15h30 à 18h30', families: []}] },
      { label: 'Vendredi', perms: [{label: 'De 7h45 à 10h45', families: []}, {label: 'De 10h à 13h', families: []}, {label: 'De 15h30 à 18h30', families: []}] }
    ];
  }

  public getWeekPermanence(whichWeek) {
    this.reinitPermanences();
    this.currentWeek = whichWeek;
    const startDate = moment().weeks(whichWeek).weekday(1).format('YYYY-MM-DD');
    this.currentMonday = moment().weeks(whichWeek).weekday(1).format('DD/MM/YYYY');
    const endDate = moment().weeks(whichWeek).weekday(7).format('YYYY-MM-DD');
    this.currentSunday = moment().weeks(whichWeek).weekday(7).format('DD/MM/YYYY');
    this.permanenceService.getWeekPermanence(startDate, endDate).subscribe(
      (result) => {
        console.log('result:', result);
        _.each(result,
          (perm) => {
            const index = moment(perm.startDate).day() - 1;
            const startHour = moment(perm.startDate).format('HH:mm');
            const endHour = moment(perm.endDate).format('HH:mm');
            if (index < 5 && index >= 0 ) {
              if (startHour === '07:45' || endHour === '10:45') {
                this.permanences[index].perms[0].families.push(perm);
              } else if (startHour === '10:00' || endHour === '13:00') {
                this.permanences[index].perms[1].families.push(perm);
              } else if (startHour === '15:30' || endHour === '18:30') {
                this.permanences[index].perms[2].families.push(perm);
              }
            }
          }
        );

      }
    );

  }

  public goToPerm(perm) {
    if (perm.status !== 'DONE') {
      this.router.navigate(['/planning/' + perm.id]);
    }
  }
}
