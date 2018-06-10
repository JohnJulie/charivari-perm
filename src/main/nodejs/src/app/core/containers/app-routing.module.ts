import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'permanence' },
  { path: 'permanence', loadChildren: '../../home/home.module#HomeModule' },
  { path: 'planning', loadChildren: '../../planning/planning.module#PlanningModule' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
