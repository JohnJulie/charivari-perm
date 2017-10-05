import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatSidenavModule } from '@angular/material';
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


@NgModule({
  declarations: [
    AppComponent,
    ValidatePermanenceComponent,
    SidenavComponent
  ],
  imports: [
    BrowserModule,
    MatSidenavModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(ROUTES)
  ],
  providers: [
    PermanenceService,
    { provide: LOCALE_ID, useValue: 'fr-FR' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
