import {
    HTTP_INTERCEPTORS,
    HttpErrorResponse,
    HttpEvent,
    HttpHandler,
    HttpInterceptor,
    HttpRequest,
} from '@angular/common/http';
import { Inject, Injectable, Provider } from '@angular/core';
import { Router } from '@angular/router';
import {
    BehaviorSubject,
    Observable,
    catchError,
    of,
    switchMap,
    throwError,
    withLatestFrom,
} from 'rxjs';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Pages } from 'src/app/shared/enums';
import { API_ERROR_KEY } from '../../shared/constants';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(
        @Inject(API_ERROR_KEY)
        private apiError$$: BehaviorSubject<null | string>,
        private authService: AuthService,
        private router: Router
    ) {}

    intercept(
        request: HttpRequest<unknown>,
        next: HttpHandler
    ): Observable<HttpEvent<unknown>> {
        if (!this.authService.initialAuthenticate) {
            return next.handle(request);
        }

        return next.handle(request).pipe(
            catchError((err: HttpErrorResponse) =>
                of(err).pipe(
                    withLatestFrom(this.authService.user$),
                    switchMap(([err, user]) => {
                        console.log('error interceptorings ', err);

                        if (err.status === 403) {
                            if (user) {
                                this.apiError$$.next(
                                    'Not enough permissions to perform this action'
                                );
                            } else {
                                if (!this.router.url.includes(Pages.Login)) {
                                    this.apiError$$.next('Not authenticated!');
                                } else {
                                    this.apiError$$.next(err.error.detail);
                                }

                                this.router.navigate([Pages.Login]);
                            }
                        } else {
                            this.apiError$$.next(err.error.detail);
                        }

                        return throwError(() => err);
                    })
                )
            )
        );
    }
}

export const ErrorInterceptorProvider: Provider = {
    provide: HTTP_INTERCEPTORS,
    useClass: ErrorInterceptor,
    multi: true,
};
