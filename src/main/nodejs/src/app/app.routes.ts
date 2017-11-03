/**
 * Created by juliepel on 19/09/2017.
 */
import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import {ValidatePermanenceComponent } from './views/validate-permanence/validate-permanence.component'
import { ChooseReplacementComponent } from './views/choose-replacement/choose-replacement.component';
import { FamiliesResolver } from './resolvers/families-resolver';

export const ROUTES: Routes = [
  { path: '', component: ValidatePermanenceComponent },
  { path: 'permanence/:id/replacement', component: ChooseReplacementComponent, resolve: { families: FamiliesResolver } }
];
