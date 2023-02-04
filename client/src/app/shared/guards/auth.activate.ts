import { Inject, Injectable } from '@angular/core';
import {
    ActivatedRouteSnapshot,
    CanActivate,
    Router,
    RouterStateSnapshot,
    UrlTree,
} from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from 'src/app/auth/services/auth.service';
import { API_ERROR_KEY } from '../constants';
import { Pages } from '../enums';

@Injectable({
    providedIn: 'root',
})
export class CanActivateAuth implements CanActivate {
    constructor(
        private authService: AuthService,
        private router: Router,
        @Inject(API_ERROR_KEY)
        private apiError$$: BehaviorSubject<null | string>
    ) {}

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): boolean | UrlTree {
        const { reqAuth } = route.data || {};

        if (
            reqAuth === undefined ||
            reqAuth === this.authService.isUserAuthenticated
        ) {
            return true;
        }

        const returnUrl = !state.url.includes(Pages.Logout)
            ? state.url
            : undefined;

        if (!this.authService.isUserAuthenticated) {
            this.apiError$$.next('User must be authenticated!');

            return this.router.createUrlTree([Pages.Login], {
                queryParams: { returnUrl },
            });
        }

        this.apiError$$.next('User already logged in!');
        return this.router.createUrlTree([Pages.Home]);
    }
}
