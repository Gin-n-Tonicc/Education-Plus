import { IUser } from './user.interface';

export interface IUserAuthResponse {
    token: string;
    student?: Omit<IUser, 'accessToken'>;
    business?: Omit<IUser, 'accessToken'>;
}
