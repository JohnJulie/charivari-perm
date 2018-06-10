import { Component, OnInit } from '@angular/core';
import { PermanenceModel } from '../../../shared/models/permanence.model';
import { PermanenceService } from '../../../shared/services/permanence/permanence.service';
import { FamilyModel } from '../../../shared/models/family.model';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { DialogComponent } from '../../../components/dialog/dialog.component';

import * as _ from 'lodash';
import * as moment from 'moment';
import { FamilyService } from '../../../shared/services/family/family.service';

@Component({
  selector: 'app-replacement-permanences',
  templateUrl: './replacement-permanences.component.html',
  styleUrls: ['./replacement-permanences.component.scss']
})
export class ReplacementPermanencesComponent implements OnInit {

  public replacements: any;
  public families: FamilyModel[];
  public nobody: FamilyModel;
  public choosePermanence: any[];

  constructor(
    private permanenceService: PermanenceService,
    private familyService: FamilyService,
    private router: Router,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.getFamilies();
  }

  private getFamilies() {
    this.familyService.getFamilies().subscribe(
      families => {
        this.families = families;
        this.nobody = _.find(this.families, ['id', 31]);
        this.getReplacements();
      }
    );
  }

  private getReplacements() {
    this.permanenceService.getReplacements().subscribe(
      (replacements: PermanenceModel[]) => {
        this.replacements = [];
        _.each(replacements, (replacement) => {
          replacement.originalFamilyImage = _.find(this.families, ['id', replacement.originalFamilyId]) ?
            _.find(this.families, ['id', replacement.originalFamilyId]).image.url : this.nobody.image.url;
          if (replacement.originalFamilyId !== replacement.family.id) {
            this.replacements.push(replacement);
          }
        });
        this.replacements = _.orderBy(this.replacements, ['startDate', 'endStart'], ['asc', 'asc']);
      }
    );
  }

  public onUpdate(event) {
    this.router.navigate(['replacements/' + event.id]);
  }

  public onSearch(event) {
    this.permanenceService.getPermanenceBySlot(
      event[0],
      event[1]
    ).subscribe(
      (result: any[]) => {
        if (result.length === 1) {
          const permToReplace = result[0];
          if (!_.find(this.replacements, ['id', permToReplace.id])) {
            permToReplace.family = this.nobody;
            this.permanenceService.updatePermanence(permToReplace).subscribe(
              () => {
                permToReplace.originalFamilyImage = _.find(this.families, ['id', permToReplace.originalFamilyId]) ?
                  _.find(this.families, ['id', permToReplace.originalFamilyId]).image.url : permToReplace.family.image.url;
                this.replacements.push(permToReplace);
                this.replacements = _.orderBy(this.replacements, ['startDate', 'endStart'], ['asc', 'asc']);
              }
            );
          }
        } else if (result.length > 1) {
          this.choosePermanence = [];
          _.each(result, (perm) => {
            perm.originalFamilyImage = _.find(this.families, ['id', perm.originalFamilyId]).image.url;
            this.choosePermanence.push(perm);
          });
          const dialogRef = this.dialog.open(DialogComponent,
            { data: { permanences: this.choosePermanence }, width: '450px', height: '250px' });
          dialogRef.afterClosed().subscribe(
            (toReplace) => {
              const permToReplace = _.omit(toReplace, ['originalFamilyImage']);
              if (!_.find(this.replacements, ['id', permToReplace.id])) {
                permToReplace.family = _.find(this.families, ['id', 31]);
                this.permanenceService.updatePermanence(permToReplace).subscribe(
                  () => {
                    permToReplace.originalFamilyImage = _.find(this.families, ['id', permToReplace.originalFamilyId]).image.url;
                    this.replacements.push(permToReplace);
                    this.replacements = _.orderBy(this.replacements, ['startDate', 'endStart'], ['asc', 'asc']);
                  }
                );
              }
            }
          );
        }
      }
    );
  }

}
