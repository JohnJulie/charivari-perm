/**
 * Created by juliepel on 08/06/2018.
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ValidationsRoutingModule } from './validations-routing.module';
import { SharedModule } from '../shared/shared.module';

import * as fromContainers from '../validations/containers';
import * as fromComponents from '../validations/components';

@NgModule({
  imports: [
    CommonModule,
    ValidationsRoutingModule,
    HttpClientModule,
    SharedModule
  ],
  declarations: [
    ...fromContainers.containers,
    ...fromComponents.components
  ],
  providers: [
  ],
})
export class ValidationsModule { }
