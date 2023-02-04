import { IBusiness } from './business.interface';
import { IUser } from './user.interface';

export interface IUserAuthResponse {
    token: string;
    student?: Omit<IUser, 'accessToken'>;
    user?: Omit<IUser, 'accessToken'>;
    business?: Omit<IBusiness, 'accessToken'>;
}
