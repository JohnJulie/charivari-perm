import { ImageModel } from '../../models/image.model';
/**
 * Created by juliepel on 19/09/2017.
 */
export interface FamilyModel {
  id: number;
  label: string;
  image: ImageModel;
  startDateContract: string;
  endDateContract: string;
}
