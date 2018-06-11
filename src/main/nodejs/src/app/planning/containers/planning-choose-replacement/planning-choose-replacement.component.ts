import { Component, OnInit } from '@angular/core';
import { PermanenceModel } from '../../../shared/models/permanence.model';
import { ActivatedRoute, Router } from '@angular/router';
import { PermanenceService } from '../../../shared/services/permanence/permanence.service';
import { FamilyService } from '../../../shared/services/family/family.service';
import { FamilyModel } from '../../../shared/models/family.model';
import * as moment from 'moment';
import * as _ from 'lodash';

@Component({
  selector: 'app-planning-choose-replacement',
  templateUrl: './planning-choose-replacement.component.html',
  styleUrls: ['./planning-choose-replacement.component.scss']
})
export class PlanningChooseReplacementComponent implements OnInit {

  public families: FamilyModel[];
  public nobody: FamilyModel;
  public permanence: PermanenceModel;

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

    this.permanenceService.getPermanence(permanenceId).subscribe(
      result => {
        this.permanence = result;
      }
    );
  }

  getFamilies() {
    this.familyService.getFamilies().subscribe(
      families => {
        this.nobody = _.find(families, ['id', 31]);
        this.families = _.filter(families, (family) => { return family.id !== 31; });
      }
    );
  }

  onChooseReplacement(family: FamilyModel) {
    const permanence = this.permanence;
    permanence.family = family;
    permanence.status = 'REPLACEMENT';
    this.permanenceService.updatePermanence(permanence).subscribe(
      //todo: if perm
      () => this.router.navigate(['/planning'])
    );
  }

}
