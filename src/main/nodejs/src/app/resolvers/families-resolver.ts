/**
 * Created by juliepel on 03/11/2017.
 */
import { Injectable } from '@angular/core';
import { Resolve } from '@angular/router';
import { FamilyModel } from '../shared/models/family.model';
import { FamilyService } from '../shared/services/family/family.service';

@Injectable()
export class FamiliesResolver implements Resolve<FamilyModel[]> {
  constructor(private familyService: FamilyService) {
  }
  resolve() {
    return this.familyService.getFamilies();
  }
}
