import {
    HTTP_INTERCEPTORS,
    HttpEvent,
    HttpHandler,
    HttpInterceptor,
    HttpRequest,
} from '@angular/common/http';
import { Injectable, Provider } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
const { apiUrl } = environment;

@Injectable()
export class RequestInterceptor implements HttpInterceptor {
    constructor() {}

    intercept(
        request: HttpRequest<unknown>,
        next: HttpHandler
    ): Observable<HttpEvent<unknown>> {
        if (request.url.startsWith('api')) {
            request = request.clone({
                url: request.url.replace('api', apiUrl),
                withCredentials: true,
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
