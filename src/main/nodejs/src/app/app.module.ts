import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { MatSidenavModule, MatGridListModule, MatDialogModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { LOCALE_ID } from '@angular/core';
import { MyDatePickerModule } from 'mydatepicker';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

/**
 * Import Components
 */
import { AppComponent } from './app.component';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { PlanningPermanenceComponent } from './views/planning-permanence/planning-permanence.component';
import { ReplacementPermanencesComponent } from './views/replacement-permanences/replacement-permanences.component';
import { ChooseReplacementComponent } from './views/choose-replacement/choose-replacement.component';

/**
 * Import Services
 */
import { PermanenceService } from './services/permanence/permanence.service';
import { FamilyService } from './services/family/family.service';

/**
 * Import Views
 */
import { ValidatePermanenceComponent } from './views/validate-permanence/validate-permanence.component';

/**
 * Import Routes
 */
import { ROUTES } from './app.routes';

/**
 * Import Resolvers
 */
import { FamiliesResolver } from './resolvers/families-resolver';
import { DialogComponent } from './components/dialog/dialog.component';
import { CurrentStateService } from './services/shared-data/current-state.service';

@NgModule({
  declarations: [
    AppComponent,
    ValidatePermanenceComponent,
    SidenavComponent,
    ChooseReplacementComponent,
    PlanningPermanenceComponent,
    ReplacementPermanencesComponent,
    DialogComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    MatSidenavModule,
    MatGridListModule,
    MyDatePickerModule,
    MatDialogModule,
    FlexLayoutModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(ROUTES),
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    PermanenceService,
    FamilyService,
    FamiliesResolver,
    CurrentStateService,
    { provide: LOCALE_ID, useValue: 'fr-FR' }
  ],
  bootstrap: [AppComponent],
  entryComponents: [DialogComponent]
})
export class AppModule { }
