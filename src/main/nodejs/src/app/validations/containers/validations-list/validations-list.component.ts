import { Component, OnInit } from '@angular/core';
import { PermanenceModel } from '../../../shared/models/permanence.model';
import { PermanenceService } from '../../../shared/services/permanence/permanence.service';
import { FamilyModel } from '../../../shared/models/family.model';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { DialogComponent } from '../../../shared/components/dialog/dialog.component';

import * as _ from 'lodash';
import * as moment from 'moment';
import { FamilyService } from '../../../shared/services/family/family.service';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-validations-list',
  templateUrl: './validations-list.component.html',
  styleUrls: ['./validations-list.component.scss']
})
export class ValidationsListComponent implements OnInit {

  public permanencesToValidate: any;
  public families: FamilyModel[];
  public nobody: FamilyModel;
  public choosePermanence: any[];
  public nobodyId = environment.nobody;

  constructor(
    private permanenceService: PermanenceService,
    private familyService: FamilyService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getFamilies();
  }

  private getFamilies() {
    this.familyService.getFamilies().subscribe(
      families => {
        this.families = families;
        this.nobody = _.find(this.families, ['id', this.nobodyId]);
        this.getPermanenceToValidate();
      }
    );
  }

  private getPermanenceToValidate() {
    this.permanenceService.getValidations().subscribe(
      (permanences: PermanenceModel[]) => {
        this.permanencesToValidate = [];
        _.each(permanences, (permanence) => {
          permanence.originalFamilyImage = _.find(this.families, ['id', permanence.originalFamilyId]) ?
            _.find(this.families, ['id', permanence.originalFamilyId]).image.url : this.nobody.image.url;
          this.permanencesToValidate.push(permanence);
        });
        this.permanencesToValidate = _.orderBy(this.permanencesToValidate, ['startDate', 'endStart'], ['desc', 'desc']);
      }
    );
  }

  public onUpdate(event) {
    this.router.navigate(['validations/' + event.id]);
  }
}
