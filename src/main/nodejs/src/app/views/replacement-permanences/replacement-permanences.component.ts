import { Component, OnInit } from '@angular/core';
import { PermanenceModel } from '../../models/permanence.model';
import { PermanenceService } from '../../services/permanence/permanence.service';
import { FamilyModel } from '../../models/family.model';
import { ActivatedRoute } from '@angular/router';
import { IMyDpOptions } from 'mydatepicker';
import { MatDialog } from '@angular/material';
import { DialogComponent } from '../../components/dialog/dialog.component';

import * as _ from 'lodash';
import * as moment from 'moment';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-replacement-permanences',
  templateUrl: './replacement-permanences.component.html',
  styleUrls: ['./replacement-permanences.component.scss']
})
export class ReplacementPermanencesComponent implements OnInit {

  public replacements: any;
  public families: Array<FamilyModel>;
  public toReplaceDate: any;
  public myDatePickerOptions: IMyDpOptions;
  public choosePermanence: Array<any>;
  public replacementForm: FormGroup;

  constructor(
    private permanenceService: PermanenceService,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.activatedRoute.data.subscribe(
      (data: Array<FamilyModel>) => {
        this.families = data['families'];
      }, error => console.log('resolve family error:', error)
    );
    this.myDatePickerOptions = {
      // other options...
      dateFormat: 'dd/mm/yyyy',
      openSelectorTopOfInput: true,
      satHighlight: true,
      disableWeekends: true,
      showWeekNumbers: true,
      editableDateField: false,
      disableUntil: {year: moment().year(), month: moment().month() + 1, day: moment().date() - 1}
    };
    this.replacementForm = this.fb.group({
      date: [null, Validators.required],
      slot: [null, Validators.required]
    });
    this.toReplaceDate = { date: { year: 2018, month: 10, day: 9 } };
    this.getReplacements();
  }

  private getReplacements() {
    this.permanenceService.getReplacements().subscribe(
      (replacements: Array<PermanenceModel>) => {
        this.replacements = [];
        _.each(replacements, (replacement) => {
          replacement.originalFamilyImage = _.find(this.families, ['id', replacement.originalFamilyId]).image.url;
          if (replacement.originalFamilyId !== replacement.family.id) {
            this.replacements.push(replacement);
          }
        });
        this.replacements = _.orderBy(this.replacements, ['startDate', 'endStart'], ['asc', 'asc']);
      }
    );
  }

  public searchPermanence() {
    const dateFormDatepicker = this.replacementForm.value.date;
    const date = moment(dateFormDatepicker.formatted).format('YYYY-MM-DD');
    this.permanenceService.getPermanenceBySlot(
      date,
      this.replacementForm.value.slot
    ).subscribe(
      (result: Array<any>) => {
        if (result.length === 1) {
          const permToReplace = result[0];
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
