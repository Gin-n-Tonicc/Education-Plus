import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
    BehaviorSubject,
    catchError,
    tap,
    throwError,
    withLatestFrom,
} from 'rxjs';
import { IBusinessRegister } from 'src/app/shared/interfaces/business-register.interface';
import { IUserAuthResponse } from 'src/app/shared/interfaces/user-auth-response.interface';
import { LocalStorageService } from 'src/app/shared/services/local-storage.service';
import { USER_STORAGE_KEY } from '../../shared/constants';
import {
    IBusiness,
    IStudentRegisterData,
    IUser,
} from '../../shared/interfaces';

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    private readonly users$$ = new BehaviorSubject<null | IUser>(
        this.localStorage.checkForValue(USER_STORAGE_KEY, true) || null
    );

    public isUserAuthenticated = false;
    public initialAuthenticate = false;
    public user: null | IUser = null;
    public user$ = this.users$$.asObservable();

    constructor(
        private httpClient: HttpClient,
        private localStorage: LocalStorageService
    ) {
        this.user$.subscribe((user) => {
            console.log(user);

            this.user = user;
            this.isUserAuthenticated = !!user;
            localStorage.setData(USER_STORAGE_KEY, user ? user : {});
        });
    }

    loginStudent(email: string, password: string) {
        return this.httpClient
            .post<IUserAuthResponse>('api/students/login', {
                email,
                password,
            })
            .pipe(
                tap((user) => {
                    const newUser: IUser = Object.assign(
                        { accessToken: user.token },
                        user.student
                    );

                    this.users$$.next(newUser);
                })
            );
    }

    loginBusiness(email: string, password: string) {
        return this.httpClient
            .post<IUserAuthResponse>('api/businesses/login', {
                email,
                password,
            })
            .pipe(
                tap((user) => {
                    const newUser = this.parseAuthResponseBusinessToUser(user);
                    this.users$$.next(newUser);
                })
            );
    }

    registerStudent(userData: IStudentRegisterData) {
        return this.httpClient
            .post<IUserAuthResponse>('api/students/register', userData)
            .pipe(
                tap((user) => {
                    const newUser: IUser = Object.assign(
                        { accessToken: user.token },
                        user.student
                    );

                    this.users$$.next(newUser);
                })
            );
    }

    registerBusiness(businessData: IBusinessRegister) {
        return this.httpClient
            .post<IUserAuthResponse>('api/businesses/register', businessData)
            .pipe(
                tap((user) => {
                    const newUser = this.parseAuthResponseBusinessToUser(user);
                    this.users$$.next(newUser);
                })
            );
    }

    logout() {
        this.clearUser();
    }

    userDetails() {
        return this.httpClient.get<IUserAuthResponse>('api/me').pipe(
            withLatestFrom(this.user$),
            tap(([userFromReq, oldUser]: [IUserAuthResponse, IUser | null]) => {
                Object.assign(oldUser || {}, {
                    ...userFromReq.user,
                    accessToken: userFromReq.token,
                });

                this.users$$.next(oldUser);
            })
        );
    }

    authenticate() {
        return this.userDetails().pipe(
            catchError((e: any) => {
                this.clearUser();
                this.initialAuthenticate = true;

                return throwError(() => e);
            }),
            tap(() => {
                this.initialAuthenticate = true;
            })
        );
    }

    private clearUser() {
        this.users$$.next(null);
    }

    private parseAuthResponseBusinessToUser(
        response: IUserAuthResponse
    ): IUser {
        const { description, placeOfResidence, ...otherData } =
            response.business as IBusiness;

        otherData.accessToken = response.token;
        return otherData;
    }
}
