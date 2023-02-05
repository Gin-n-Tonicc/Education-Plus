import { IBusiness } from './business.interface';

export interface IFollow {
    id: number;
    gotFollowedBusiness: IBusiness;
    followedStudent: any;
}
