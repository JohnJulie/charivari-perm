import { Component, OnInit } from '@angular/core';
import { FamilyModel } from '../../models/family.model';
import { FamilyService } from '../../services/family/family.service';
import { ActivatedRoute, NavigationEnd, NavigationStart, ParamMap, Params, Router } from '@angular/router';
import { PermanenceService } from '../../services/permanence/permanence.service';
import { PermanenceModel } from '../../models/permanence.model';
import * as moment from 'moment';
import * as _ from 'lodash';
import { CurrentStateService } from '../../services/shared-data/current-state.service';

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
  public previousUrl: string;

  constructor(
    private familyService: FamilyService,
    private permanenceService: PermanenceService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private currentStateService: CurrentStateService
  ) {}

  ngOnInit() {
    this.currentStateService.state.subscribe(
      currentState => {
        console.log('currentState:', currentState);
        this.previousUrl = currentState;
      }
    );
    this.permanenceId = this.activatedRoute.snapshot.paramMap.get('id');

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
        // reorder families to sort by label
        this.familiesGrid = new Array(data['families'].length);
        this.families = _.orderBy(data['families'], ['label'], ['asc']);
        const anybodyFamily = _.remove(this.families, (n) => { return n.id === 31; });
        this.families = _.concat(anybodyFamily, this.families);
        _.each(this.families, (family, index) => {
          if (family.id !== 31) {
            this.familiesGrid[index] = {family: family, cols: 1, rows: 1};
          } else {
            this.familiesGrid[index] = {family: family, cols: 6, rows: 1};
          }
        });
      }, error => console.log('resolve family error:', error)
    );
  }

  public setFamilyToPermanence(family: FamilyModel) {
    const permanence = this.permanence;
    permanence.family = family;
    permanence.status = 'REPLACEMENT';
    this.permanenceService.updatePermanence(permanence).subscribe(
      //todo: if perm
      () => this.router.navigate([this.previousUrl])
    );
  }

}
