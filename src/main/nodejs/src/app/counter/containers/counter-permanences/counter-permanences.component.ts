import { Component, OnInit } from '@angular/core';
import { FamilyModel } from '../../../shared/models/family.model';
import { PermanenceService } from '../../../shared/services/permanence/permanence.service';
import { FamilyService } from '../../../shared/services/family/family.service';

import * as moment from 'moment';
import * as _ from 'lodash';
import { PermanenceStatus } from '../../../shared/models/permanence-status.model';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-counter-permanences',
  templateUrl: './counter-permanences.component.html',
  styleUrls: ['./counter-permanences.component.scss']
})
export class CounterPermanencesComponent implements OnInit {

  public familyCount: any[];
  public families: FamilyModel[];
  public nobodyId = environment.nobody;
  public prosId = environment.pros;

  constructor(
    private permanenceService: PermanenceService,
    private familyService: FamilyService
  ) { }

  ngOnInit() {
    this.getFamilies();
  }

  getFamilies() {
    this.familyService.getFamilies().subscribe(
      families => {
        this.families = _.chain(families)
          .filter((family) => { return (family.id !== this.nobodyId) })
          .filter((family) => { return (family.id !== this.prosId) })
          .orderBy(['label'], ['asc'])
          .value();
        const counterFamilies = [];
        _.each(this.families, (family) => {
          this.permanenceService.getCountPermByFamily(family.id).subscribe(
            perms => {
              const donePerms = _.filter(perms, ['status', PermanenceStatus.done]);
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

              counterFamilies.push({family: family, count: donePermsCount - originalPermCount});
              this.familyCount = counterFamilies;
            }
          );
        });
        console.log('final:', counterFamilies);
      }
    );
  }

}
