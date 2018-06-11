/**
 * Created by juliepel on 08/06/2018.
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ReplacementRoutingModule } from './replacement-routing.module';
import { SharedModule } from '../shared/shared.module';

import * as fromContainers from '../replacement/containers';
import * as fromComponents from '../replacement/components';
import { ReplacementChooseComponent } from './containers/replacement-choose/replacement-choose.component';
import { DialogComponent } from '../shared/components/dialog/dialog.component';

@NgModule({
  imports: [
    CommonModule,
    ReplacementRoutingModule,
    HttpClientModule,
    SharedModule
  ],
  declarations: [
    ...fromContainers.containers,
    ...fromComponents.components
  ],
  providers: [
  ],
  entryComponents: [DialogComponent]
})
export class ReplacementModule { }
