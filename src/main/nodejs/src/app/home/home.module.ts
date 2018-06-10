/**
 * Created by juliepel on 08/06/2018.
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { HomeRoutingModule } from './home-routing.module';
import { SharedModule } from '../shared/shared.module';

import * as fromContainers from '../home/containers';

@NgModule({
  imports: [
    CommonModule,
    HomeRoutingModule,
    HttpClientModule,
    SharedModule
  ],
  declarations: [
    ...fromContainers.containers
  ],
  providers: [
  ]
})
export class HomeModule { }
