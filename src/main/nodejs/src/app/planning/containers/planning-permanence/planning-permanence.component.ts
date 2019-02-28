import { Component, OnInit } from '@angular/core';
import { PermanenceService } from '../../../shared/services/permanence/permanence.service';
import * as moment from 'moment';
import * as _ from 'lodash';
import { Router } from '@angular/router';
import { StorageService } from '../../../shared/services/storage/storage.service';
import { AccountType } from '../../../shared/models/account-type.model';
import { PermanenceStatus } from '../../../shared/models/permanence-status.model';
import { environment } from '../../../../environments/environment';

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
  public isAdmin: boolean;
  public nobodyId = environment.nobody;
  public startYear = environment.startYear;
  public endYear = environment.endYear;

  constructor(
    private permanenceService: PermanenceService,
    private storageService: StorageService,
    private router: Router
  ) { }

  ngOnInit() {
    moment.locale('fr');
    this.isAdmin = this.storageService.read('cpu').type === AccountType.admin;
    this.nowWeek = moment().isoWeek();
    this.getWeekPermanence(this.nowWeek);
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
    const startDate = moment().weeks(whichWeek).weekday(0).format('YYYY-MM-DD');
    this.currentMonday = moment().weeks(whichWeek).weekday(0).format('DD/MM/YYYY');
    const endDate = moment().weeks(whichWeek).weekday(4).format('YYYY-MM-DD');
    this.currentSunday = moment().weeks(whichWeek).weekday(4).format('DD/MM/YYYY');
    
    this.permanenceService.getWeekPermanence(startDate, endDate).subscribe(
      (result) => {
        console.log('result:', result);
        _.each(result,
          (perm) => {
            const index = moment(perm.startDate).day() - 1;
            const startHour = moment(perm.startDate).format('HH:mm');
            const endHour = moment(perm.endDate).format('HH:mm');
            if (index < 5 && index >= 0 ) {
              if (moment(endHour, 'HH:mm').isBetween(moment('07:45', 'HH:mm'), moment('10:45', 'HH:mm'), null, '[]')) {
                this.permanences[index].perms[0].families.push(perm);
              } else if (moment(endHour, 'HH:mm').isBetween(moment('10:00', 'HH:mm'), moment('13:00', 'HH:mm'), null, '[]')) {
                this.permanences[index].perms[1].families.push(perm);
              } else if (moment(endHour, 'HH:mm').isBetween(moment('15:30', 'HH:mm'), moment('18:30', 'HH:mm'), null, '[]')) {
                this.permanences[index].perms[2].families.push(perm); 
              }
            }
          }
        );
      }
    );

  }

  public goToPerm(perm) {
    if (this.isAdmin || perm.status !== PermanenceStatus.done) {
      this.router.navigate(['/planning/' + perm.id]);
    }
  }

  public goToFlyingPerm(perm) {
  	this.router.navigate(['/planning/' + perm.families[0].startDate +  '/flying-perm']);
  }
}
