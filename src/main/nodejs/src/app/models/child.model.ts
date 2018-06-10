import { FamilyModel } from '../shared/models/family.model';

export interface ChildModel {
  id: number;
  lastname: string;
  firstname: string;
  birthday: string;
  imageName: string;
  family: FamilyModel;
}
