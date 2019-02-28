import { Component, OnInit } from '@angular/core';
import { PermanenceModel } from '../../../shared/models/permanence.model';
import { ActivatedRoute, Router } from '@angular/router';
import { PermanenceService } from '../../../shared/services/permanence/permanence.service';
import { FamilyService } from '../../../shared/services/family/family.service';
import { FamilyModel } from '../../../shared/models/family.model';
import * as moment from 'moment';
import * as _ from 'lodash';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-planning-choose-replacement',
  templateUrl: './planning-choose-replacement.component.html',
  styleUrls: ['./planning-choose-replacement.component.scss']
})
export class PlanningChooseReplacementComponent implements OnInit {

  public families: FamilyModel[];
  public nobody: FamilyModel;
  public pros: FamilyModel;
  public permanence: PermanenceModel;
  public nobodyId = environment.nobody;
  public prosId = environment.pros;
  public isFlyingPerm = false;

  constructor(
    private familyService: FamilyService,
    private permanenceService: PermanenceService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.getFamilies();
    this.getPermanence();
  }

  getPermanence() {
     const permanenceId = this.activatedRoute.snapshot.paramMap.get('id');
     const permanenceDate = this.activatedRoute.snapshot.paramMap.get('date');

    if (permanenceId) {
      this.permanenceService.getPermanence(permanenceId).subscribe(
        result => {
          this.permanence = result;
          console.log('this.permanence:', this.permanence);
        }
      );
    }

    if (permanenceDate) {
      this.isFlyingPerm = true;
      this.permanence = {
        id: 0,
        originalFamilyId: this.nobodyId,
        startDate: permanenceDate,
        endDate: moment(Number(permanenceDate)).add(3, 'hours').valueOf().toString(),
        status: 'NOT_CONFIRMED',
        family: null,
        isOpen: true
      };
    }
  }

  getFamilies() {
    this.familyService.getFamilies().subscribe(
      families => {
        this.nobody = _.find(families, ['id', this.nobodyId]);
        this.pros = _.find(families, ['id', this.prosId]);
        this.families = _.filter(families, (family) => { return family.id !== (this.nobodyId && this.prosId); });
      }
    );
  }

  onChooseReplacement(family: FamilyModel) {
    const permanence = this.permanence;
    permanence.family = family;
    if (!this.isFlyingPerm) {
      permanence.status = 'REPLACEMENT';
      this.permanenceService.updatePermanence(permanence).subscribe(
        //todo: if perm
        () => this.router.navigate(['/planning'])
      );
    } else {
      this.permanenceService.setFlyingPerm(this.nobodyId, family.id, this.permanence.startDate).subscribe(
        //todo: if perm
        () => this.router.navigate(['/planning'])
      );
    }
    
  }

}
