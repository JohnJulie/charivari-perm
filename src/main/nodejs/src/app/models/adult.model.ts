import { FamilyModel } from '../shared/models/family.model';

 /**
 * Created by juliepel on 19/09/2017.
 */
export interface AdultModel {
    id: number;
    lastname: string;
    firstname: string;
    birthday: string;
    numberPhone: string;
    mail: string;
    family: FamilyModel;
}
