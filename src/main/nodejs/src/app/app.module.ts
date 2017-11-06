import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { MatSidenavModule, MatGridListModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { LOCALE_ID } from '@angular/core';

/**
 * Import Components
 */
import { AppComponent } from './app.component';
import { SidenavComponent } from './components/sidenav/sidenav.component';

/**
 * Import Services
 */
import { PermanenceService } from './services/permanence/permanence.service';

/**
 * Import Views
 */
import { ValidatePermanenceComponent } from './views/validate-permanence/validate-permanence.component';

/**
 * Import Routes
 */
import { ROUTES } from './app.routes';
import { ChooseReplacementComponent } from './views/choose-replacement/choose-replacement.component';
import { FamilyService } from './services/family/family.service';
import { FamiliesResolver } from './resolvers/families-resolver';
import { PlanningPermanenceComponent } from './views/planning-permanence/planning-permanence.component';


@NgModule({
  declarations: [
    AppComponent,
    ValidatePermanenceComponent,
    SidenavComponent,
    ChooseReplacementComponent,
    PlanningPermanenceComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    MatSidenavModule,
    MatGridListModule,
    FlexLayoutModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(ROUTES)
  ],
  providers: [
    PermanenceService,
    FamilyService,
    FamiliesResolver,
    { provide: LOCALE_ID, useValue: 'fr-FR' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
