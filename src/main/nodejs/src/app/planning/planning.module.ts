/**
 * Created by juliepel on 08/06/2018.
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { PlanningRoutingModule } from './planning-routing.module';
import { SharedModule } from '../shared/shared.module';

import * as fromContainers from '../planning/containers';

@NgModule({
  imports: [
    CommonModule,
    PlanningRoutingModule,
    HttpClientModule,
    SharedModule
  ],
  declarations: [
    ...fromContainers.containers
  ],
  providers: [
  ]
})
export class PlanningModule { }
