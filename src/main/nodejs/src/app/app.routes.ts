/**
 * Created by juliepel on 19/09/2017.
 */
import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { ValidatePermanenceComponent } from './views/validate-permanence/validate-permanence.component'
import { ChooseReplacementComponent } from './views/choose-replacement/choose-replacement.component';
import { FamiliesResolver } from './resolvers/families-resolver';
import { PlanningPermanenceComponent } from './views/planning-permanence/planning-permanence.component';
import { ReplacementPermanencesComponent } from './views/replacement-permanences/replacement-permanences.component';

export const ROUTES: Routes = [
  { path: '', redirectTo: 'permanence', pathMatch: 'full' },
  { path: 'permanence', component: ValidatePermanenceComponent },
  { path: 'permanence/:id', component: ValidatePermanenceComponent },
  { path: 'permanence/:id/replacement', component: ChooseReplacementComponent, resolve: { families: FamiliesResolver } },
  { path: 'planning', component: PlanningPermanenceComponent },
  { path: 'replacements', component: ReplacementPermanencesComponent, resolve: { families: FamiliesResolver } }
];
