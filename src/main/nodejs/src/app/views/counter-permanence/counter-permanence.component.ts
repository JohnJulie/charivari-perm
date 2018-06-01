import { Component, OnInit } from '@angular/core';
import { PermanenceService } from '../../services/permanence/permanence.service';
import { PermanenceModel } from '../../models/permanence.model';
import * as moment from 'moment';
import * as _ from 'lodash';
import { FamilyService } from '../../services/family/family.service';
import { FamilyModel } from '../../models/family.model';
import { ActivatedRoute } from '@angular/router';
import { CurrentStateService } from '../../services/shared-data/current-state.service';

@Component({
  selector: 'app-counter-permanence',
  templateUrl: './counter-permanence.component.html',
  styleUrls: ['./counter-permanence.component.scss']
})
export class CounterPermanenceComponent implements OnInit {

  public familyCount: Array<any>;
  public familiesGrid: Array<any>;
  public families: Array<FamilyModel>;
  public colNumber: number;

  constructor(
    private permanenceService: PermanenceService,
    private stateService: CurrentStateService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.stateService.updateState(3);
    this.colNumber = 5;
    this.familyCount = [];
    this.activatedRoute.data.subscribe(
      (data: Array<FamilyModel>) => {

        this.familyCount = [];
        this.families = _.orderBy(data['families'], ['label'], ['asc']);
        const anybodyFamily = _.remove(this.families, (n) => { return n.id === 31; });
        this.families = _.concat(anybodyFamily, this.families);
        _.each(this.families, (family, index) => {
          if (family.id !== 31) {
            this.permanenceService.getCountPermByFamily(family.id).subscribe(
              perms => {
                const donePerms = _.filter(perms, ['status', 'DONE']);
                let donePermsMinutes = 0;
                _.each(donePerms, (perm) => {
                  donePermsMinutes += moment(perm.endDate).diff(moment(perm.startDate), 'minutes');
                });
                const donePermsCount = donePermsMinutes / 180;

                const originalPerm = _.filter(perms, ['originalFamilyId', family.id]);
                let originalPermMinutes = 0;
                _.each(originalPerm, (perm) => {
                  originalPermMinutes += moment(perm.endDate).diff(moment(perm.startDate), 'minutes');
                });
                const originalPermCount = originalPermMinutes / 180;

                this.familyCount.push({family: family, count: donePermsCount - originalPermCount});
              }
            );
          }
        });
      }, error => console.log('resolve family error:', error)
    );
    _.forEach(this.families, (family) => {

    });
  }

  onResize(event) {
    const element = event.target.innerWidth;

    if (element < 950) {
      this.colNumber = 4;
    }

    if (element > 950) {
      this.colNumber = 5;
    }

    if (element < 750) {
      this.colNumber = 2;
    }
  }

}
