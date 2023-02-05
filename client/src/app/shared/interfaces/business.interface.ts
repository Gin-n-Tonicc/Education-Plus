import { IUser } from './user.interface';

export interface IBusiness extends Omit<IUser, 'fullName'> {
    name: string;
    description: string;
    placeOfResidence: string;
    verified: boolean;
}
