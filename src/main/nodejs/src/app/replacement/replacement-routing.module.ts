/**
 * Created by juliepel on 08/06/2018.
 */
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ReplacementPermanencesComponent } from './containers/replacement-permanences/replacement-permanences.component';
import { ReplacementChooseComponent } from './containers/replacement-choose/replacement-choose.component';

const routes: Routes = [
  { path: '', component: ReplacementPermanencesComponent },
  { path: ':id', component: ReplacementChooseComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReplacementRoutingModule { }
