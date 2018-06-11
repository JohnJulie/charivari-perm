/**
 * Created by juliepel on 08/06/2018.
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { CounterRoutingModule } from './counter-routing.module';
import { SharedModule } from '../shared/shared.module';

import * as fromContainers from '../counter/containers';
import * as fromComponents from '../counter/components';

@NgModule({
  imports: [
    CommonModule,
    CounterRoutingModule,
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
export class CounterModule { }
