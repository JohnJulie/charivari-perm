/**
 * Created by juliepel on 14/06/2019.
 */


import { ValidationsListComponent } from './validations-list/validations-list.component';
import { ValidationsPermanencesComponent } from './validations-permanences/validations-permanences.component';
import { ValidationsChooseReplacementComponent } from './validations-choose/validations-choose.component';
export const containers: any[] = [
  ValidationsListComponent,
  ValidationsPermanencesComponent,
  ValidationsChooseReplacementComponent
];

export * from './validations-permanences/validations-permanences.component';
export * from './validations-choose/validations-choose.component';
export * from './validations-list/validations-list.component';
