import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, tap, throwError } from 'rxjs';
import { LocalStorageService } from 'src/app/shared/services/local-storage.service';
import { USER_STORAGE_KEY } from '../../shared/constants';
import { IUser } from '../../shared/interfaces';

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
            this.user = user;
            this.isUserAuthenticated = !!user;
            localStorage.setData(USER_STORAGE_KEY, user ? user : {});
        });
    }

    login(email: string, password: string) {
        return this.httpClient
            .post<IUser>('api/users/login', {
                email,
                password,
            })
            .pipe(tap((user) => this.users$$.next(user)));
    }

    register(email: string, password: string) {
        return this.httpClient
            .post<IUser>('api/users/register', {
                email,
                password,
            })
            .pipe(tap((user) => this.users$$.next(user)));
    }

    logout() {
        return this.httpClient.get<undefined>('api/users/logout').pipe(
            catchError((e: any) => {
                this.clearUser();
                return throwError(() => e);
            }),
            tap(() => {
                this.clearUser();
            })
        );
    }

    private clearUser() {
        this.users$$.next(null);
    }
}
