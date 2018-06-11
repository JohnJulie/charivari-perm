/**
 * Created by juliepel on 08/06/2018.
 */
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PlanningPermanenceComponent } from './containers/planning-permanence/planning-permanence.component';
import { PlanningValidationComponent } from './containers/planning-validation/planning-validation.component';
import { PlanningChooseReplacementComponent } from './containers/planning-choose-replacement/planning-choose-replacement.component';

const routes: Routes = [
  { path: '', component: PlanningPermanenceComponent },
  { path: ':id', component: PlanningValidationComponent },
  { path: ':id/replacement', component: PlanningChooseReplacementComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PlanningRoutingModule { }
