import { Component, OnInit } from '@angular/core';
import { FamilyModel } from '../../models/family.model';
import { FamilyService } from '../../services/family/family.service';
import { ActivatedRoute, ParamMap, Params, Router } from '@angular/router';
import { PermanenceService } from '../../services/permanence/permanence.service';
import { PermanenceModel } from '../../models/permanence.model';
import * as moment from 'moment';
import * as _ from 'lodash';

@Component({
  selector: 'app-choose-replacement',
  templateUrl: './choose-replacement.component.html',
  styleUrls: ['./choose-replacement.component.scss']
})
export class ChooseReplacementComponent implements OnInit {

  public families: Array<FamilyModel>;
  public familiesGrid: Array<any>;
  public permanence: PermanenceModel;
  public permanenceId: string;
  public startDate: any;
  public endDate: any;

  constructor(
    private familyService: FamilyService,
    private permanenceService: PermanenceService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {

    this.permanenceId = this.activatedRoute.snapshot.paramMap.get('id');
    this.familiesGrid = new Array(16);
    this.permanenceService.getPermanence(this.permanenceId).subscribe(
      result => {
        this.permanence = result;
        this.startDate = moment(_.toString(this.permanence.startDate), 'x');
        this.endDate = moment(_.toString(this.permanence.endDate), 'x');
        console.log('this.permanence:', this.permanence);
      }
    );
    this.activatedRoute.data.subscribe(
      data => {
        this.families = data['families'];
        _.each(this.families, (family, index) => {
          if (family.id !== 31) {
            this.familiesGrid[index] = {family: family, cols: 1, rows: 1};
          } else {
            this.familiesGrid[index] = {family: family, cols: 5, rows: 1};
          }
        });
        console.log('this.familiesGrid:', this.familiesGrid);
      }, error => console.log('resolve family error:', error)
    );
  }

  public setFamilyToPermanence(family: FamilyModel) {
    const permanence = this.permanence;
    console.log('family:', family);
    permanence.family = family;
    permanence.status = 'REPLACEMENT';
    this.permanenceService.updatePermanence(permanence).subscribe(
      () => this.router.navigate([''])
    );
  }

}
