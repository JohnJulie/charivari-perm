import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';

import { DialogComponent } from '../../components/dialog/dialog.component';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';

import * as fromComponents from '../components';
import { MatToolbarModule } from '@angular/material';

registerLocaleData(localeFr);

@NgModule({
  declarations: [
    AppComponent,
    DialogComponent,
    ...fromComponents.components
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent],
  entryComponents: [DialogComponent]
})
export class AppModule { }
