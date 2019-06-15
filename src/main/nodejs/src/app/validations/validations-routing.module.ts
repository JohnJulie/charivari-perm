/**
 * Created by juliepel on 08/06/2018.
 */
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ValidationsPermanencesComponent } from './containers/validations-permanences/validations-permanences.component';
import { ValidationsChooseReplacementComponent } from './containers/validations-choose/validations-choose.component';
import { ValidationsListComponent } from './containers/validations-list/validations-list.component';

const routes: Routes = [
  { path: '', component: ValidationsListComponent },
  { path: ':id', component: ValidationsPermanencesComponent },
  { path: ':id/replacement', component: ValidationsChooseReplacementComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ValidationsRoutingModule { }
