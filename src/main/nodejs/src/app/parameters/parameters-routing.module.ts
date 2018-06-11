/**
 * Created by juliepel on 08/06/2018.
 */
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParametersPermanencesComponent } from './containers/parameters-permanences/parameters-permanences.component';

const routes: Routes = [
  { path: '', component: ParametersPermanencesComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParametersRoutingModule { }
