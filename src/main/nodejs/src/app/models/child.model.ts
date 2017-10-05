import { FamilyModel } from './family.model';

export interface ChildModel {
  id: number;
  lastname: string;
  firstname: string;
  birthday: string;
  imageName: string;
  family: FamilyModel;
}
