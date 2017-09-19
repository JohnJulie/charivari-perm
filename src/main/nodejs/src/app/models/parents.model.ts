import { AdultModel } from './adult.model';
import { ChildModel } from './child.model';
/**
 * Created by juliepel on 19/09/2017.
 */
export interface ParentsModel {
  id: number;
  parents: Array<AdultModel>;
  children: Array<ChildModel>;
}
