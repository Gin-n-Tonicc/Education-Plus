import { IBusiness } from './business.interface';
import { IStudent } from './student.interface';

export interface IFollow {
    id: number;
    gotFollowedBusiness: IBusiness;
    followedStudent: IStudent;
}
