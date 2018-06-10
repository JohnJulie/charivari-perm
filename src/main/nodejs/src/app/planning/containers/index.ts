/**
 * Created by juliepel on 08/06/2018.
 */
import { PlanningPermanenceComponent } from './planning-permanence/planning-permanence.component';
import { PlanningValidationComponent } from './planning-validation/planning-validation.component';
import { PlanningChooseReplacementComponent } from './planning-choose-replacement/planning-choose-replacement.component';

export const containers: any[] = [
  PlanningPermanenceComponent,
  PlanningValidationComponent,
  PlanningChooseReplacementComponent
];

export * from './planning-permanence/planning-permanence.component';
export * from './planning-validation/planning-validation.component';
export * from './planning-choose-replacement/planning-choose-replacement.component';

