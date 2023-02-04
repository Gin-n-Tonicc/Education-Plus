import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import { AppComponent } from './app.component';
import { ErrorInterceptorProvider } from './core/interceptors/error.interceptor';
import { ManageHttpInterceptor } from './core/interceptors/manage-http.interceptor';
import { RequestInterceptorProvider } from './core/interceptors/request.interceptor';
import { API_ERROR_KEY } from './shared/constants';
import { SharedModule } from './shared/shared.module';

@NgModule({
    declarations: [AppComponent],
    imports: [HttpClientModule, BrowserModule, SharedModule],
    providers: [
        {
            provide: API_ERROR_KEY,
            useValue: new BehaviorSubject<null | unknown>(null),
        },
        ManageHttpInterceptor,
        RequestInterceptorProvider,
        ErrorInterceptorProvider,
    ],
    bootstrap: [AppComponent],
})
export class AppModule {}
