import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { LOCALE_ID } from '@angular/core';

/**
 * Import Components
 */
import { AppComponent } from './app.component';
import { CheckPermanenceComponent } from './components/check-permanence/check-permanence.component';
import { PermanenceReplacementComponent } from './components/permanence-replacement/permanence-replacement.component';

/**
 * Import Views
 */
import { ValidatePermanenceComponent } from './views/validate-permanence/validate-permanence.component';

/**
 * Import Routes
 */
import { ROUTES } from './app.routes';


@NgModule({
  declarations: [
    AppComponent,
    CheckPermanenceComponent,
    PermanenceReplacementComponent,
    ValidatePermanenceComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(ROUTES)
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'fr-FR' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
