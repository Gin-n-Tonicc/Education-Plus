import {
    HTTP_INTERCEPTORS,
    HttpEvent,
    HttpHandler,
    HttpInterceptor,
    HttpRequest,
    HttpResponse,
} from '@angular/common/http';
import { Injectable, Provider } from '@angular/core';
import { ActivationEnd, Router } from '@angular/router';
import {
    Observable,
    catchError,
    filter,
    takeUntil,
    tap,
    throwError,
} from 'rxjs';
import { AuthService } from 'src/app/auth/services/auth.service';
import { HttpCancelService } from '../services/http-cancel.service';
import { SpinnerService } from '../services/spinner.service';

@Injectable()
export class ManageHttpInterceptor implements HttpInterceptor {
    constructor(
        router: Router,
        private httpCancelService: HttpCancelService,
        private spinnerService: SpinnerService,
        private authService: AuthService
    ) {
        router.events
            .pipe(filter((e): e is ActivationEnd => e instanceof ActivationEnd))
            .subscribe(() => {
                this.httpCancelService.cancelPendingRequests();
            });
    }

    intercept(
        request: HttpRequest<unknown>,
        next: HttpHandler
    ): Observable<HttpEvent<unknown>> {
        this.spinnerService.increment();

        return next.handle(request).pipe(
            catchError((e: any) => {
                this.spinnerService.decrement();
                return throwError(() => e);
            }),
            takeUntil(this.httpCancelService.onCancelPendingRequests()),
            tap((event: HttpEvent<any>) => {
                if (event instanceof HttpResponse) {
                    this.spinnerService.decrement();
                }
            })
        );
    }
}

export const ManageHttpInterceptorProvider: Provider = {
    provide: HTTP_INTERCEPTORS,
    useClass: ManageHttpInterceptor,
    multi: true,
};
