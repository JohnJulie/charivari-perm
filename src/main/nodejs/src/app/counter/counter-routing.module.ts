/**
 * Created by juliepel on 08/06/2018.
 */
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CounterPermanencesComponent } from './containers/counter-permanences/counter-permanences.component';

const routes: Routes = [
  { path: '', component: CounterPermanencesComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CounterRoutingModule { }
