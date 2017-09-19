/**
 * Created by juliepel on 19/09/2017.
 */
import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import {ValidatePermanenceComponent } from './views/validate-permanence/validate-permanence.component'

export const ROUTES: Routes = [
  { path: '', component: ValidatePermanenceComponent }
];
