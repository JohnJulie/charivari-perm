/**
 * Created by juliepel on 08/06/2018.
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import * as fromComponents from '../shared/components';
import * as fromServices from '../shared/services';
import { MatDialogModule, MatGridListModule, MatToolbarModule } from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MyDatePickerModule } from 'mydatepicker';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    MatToolbarModule,
    MatGridListModule,
    MatDialogModule,
    FormsModule,
    MyDatePickerModule,
    ReactiveFormsModule
  ],
  declarations: [
    ...fromComponents.components
  ],
  providers: [
    ...fromServices.services
  ],
  exports: [
    ...fromComponents.components,
    MatToolbarModule,
    MatGridListModule,
    MatDialogModule,
    FormsModule,
    MyDatePickerModule,
    ReactiveFormsModule
  ]
})
export class SharedModule { }
