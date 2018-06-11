/**
 * Created by juliepel on 08/06/2018.
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ParametersRoutingModule } from './parameters-routing.module';

import { SharedModule } from '../shared/shared.module';

import * as fromContainers from '../parameters/containers';
import * as fromComponents from '../parameters/components';


@NgModule({
  imports: [
    CommonModule,
    ParametersRoutingModule,
    HttpClientModule,
    SharedModule
  ],
  declarations: [
    ...fromContainers.containers,
    ...fromComponents.components
  ],
  providers: [
  ]
})
export class ParametersModule { }
