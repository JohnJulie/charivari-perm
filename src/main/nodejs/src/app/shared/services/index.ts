/**
 * Created by juliepel on 08/06/2018.
 */

import { FamilyService } from './family/family.service';
import { PermanenceService } from './permanence/permanence.service';
import { StorageService } from './storage/storage.service';

export const services: any[] = [
  FamilyService,
  PermanenceService,
  StorageService
];

export * from './family/family.service';
export * from './permanence/permanence.service';
export * from './storage/storage.service';
