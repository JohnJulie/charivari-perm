import { Component, OnInit } from '@angular/core';
import { PermanenceModel } from '../../../shared/models/permanence.model';
import { FamilyModel } from '../../../shared/models/family.model';
import { ActivatedRoute, Router } from '@angular/router';
import { PermanenceService } from '../../../shared/services/permanence/permanence.service';
import { FamilyService } from '../../../shared/services/family/family.service';

import * as _ from 'lodash';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-home-choose-replacement',
  templateUrl: './home-choose-replacement.component.html',
  styleUrls: ['./home-choose-replacement.component.scss']
})
export class HomeChooseReplacementComponent implements OnInit {

  public families: FamilyModel[];
  public nobody: FamilyModel;
  public permanence: PermanenceModel;
  public nobodyId = environment.nobody;

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
        this.nobody = _.find(families, ['id', this.nobodyId]);
        this.families = _.filter(families, (family) => { return family.id !== this.nobodyId; });
      }
    );
  }

  onChooseReplacement(family: FamilyModel) {
    const permanence = this.permanence;
    permanence.family = family;
    permanence.status = 'REPLACEMENT';
    this.permanenceService.updatePermanence(permanence).subscribe(
      //todo: if perm
      () => this.router.navigate(['/permanence'])
    );
  }

}
