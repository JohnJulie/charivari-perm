import { FamilyModel } from './family.model';
/**
 * Created by juliepel on 19/09/2017.
 */
export interface PermanenceModel {
  id: number;
  startDate: string;
  endDate: string;
  status: string;
  family: FamilyModel;
  originalFamilyId: number;
}
