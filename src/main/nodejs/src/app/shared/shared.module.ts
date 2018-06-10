/**
 * Created by juliepel on 08/06/2018.
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import * as fromComponents from '../shared/components';
import * as fromServices from '../shared/services';
import { MatGridListModule, MatToolbarModule } from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    MatToolbarModule,
    MatGridListModule
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
    MatGridListModule
  ]
})
export class SharedModule { }
