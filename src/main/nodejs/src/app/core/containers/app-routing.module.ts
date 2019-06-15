import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../guards/auth.guards';
import { AccountGuard } from '../guards/account.guards';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  { path: 'login', loadChildren: '../../login/login.module#LoginModule'},
  { path: 'permanence', loadChildren: '../../home/home.module#HomeModule', canActivate: [AuthGuard] },
  { path: 'planning', loadChildren: '../../planning/planning.module#PlanningModule', canActivate: [AuthGuard] },
  { path: 'replacements', loadChildren: '../../replacement/replacement.module#ReplacementModule', canActivate: [AuthGuard] },  { path: 'replacements', loadChildren: '../../replacement/replacement.module#ReplacementModule', canActivate: [AuthGuard] },
  { path: 'validations', loadChildren: '../../validations/validations.module#ValidationsModule', canActivate: [AuthGuard] },
  { path: 'counter', loadChildren: '../../counter/counter.module#CounterModule', canActivate: [AuthGuard] },
  { path: 'parameters', loadChildren: '../../parameters/parameters.module#ParametersModule', canActivate: [AuthGuard, AccountGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
