/**
 * Created by juliepel on 08/06/2018.
 */
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeValidationComponent } from './containers/home-validation/home-validation.component';
import { HomeChooseReplacementComponent } from './containers/home-choose-replacement/home-choose-replacement.component';

const routes: Routes = [
  { path: '', component: HomeValidationComponent },
  { path: ':id', component: HomeChooseReplacementComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
