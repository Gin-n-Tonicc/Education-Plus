import { IUser } from './user.interface';

export interface IBusiness extends IUser {
    description: string;
    placeOfResidence: string;
}
