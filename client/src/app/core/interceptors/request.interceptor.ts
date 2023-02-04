import {
    HTTP_INTERCEPTORS,
    HttpEvent,
    HttpHandler,
    HttpInterceptor,
    HttpRequest,
} from '@angular/common/http';
import { Injectable, Provider } from '@angular/core';
import { Observable } from 'rxjs';
import { USER_STORAGE_KEY } from 'src/app/shared/constants';
import { LocalStorageService } from 'src/app/shared/services/local-storage.service';
import { environment } from 'src/environments/environment';
const { apiUrl } = environment;

@Injectable()
export class RequestInterceptor implements HttpInterceptor {
    constructor(private localStorageService: LocalStorageService) {}

    intercept(
        request: HttpRequest<unknown>,
        next: HttpHandler
    ): Observable<HttpEvent<unknown>> {
        if (request.url.startsWith('api')) {
            const user = JSON.parse(
                this.localStorageService.getData(USER_STORAGE_KEY) || '{}'
            );

            request = request.clone({
                url: request.url.replace('api', apiUrl),
                setHeaders: user.accessToken
                    ? { Authorization: user.accessToken }
                    : undefined,
            });
        }

        return next.handle(request);
    }
}

export const RequestInterceptorProvider: Provider = {
    provide: HTTP_INTERCEPTORS,
    useClass: RequestInterceptor,
    multi: true,
};
