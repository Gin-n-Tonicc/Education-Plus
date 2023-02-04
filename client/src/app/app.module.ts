import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BehaviorSubject } from 'rxjs';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from './auth/auth.module';
import { CoreModule } from './core/core.module';
import { ErrorInterceptorProvider } from './core/interceptors/error.interceptor';
import { RequestInterceptorProvider } from './core/interceptors/request.interceptor';
import { API_ERROR_KEY } from './shared/constants';
import { SharedModule } from './shared/shared.module';

@NgModule({
    declarations: [AppComponent],
    imports: [
        NgbModule,
        BrowserModule,
        RouterModule,
        AppRoutingModule,
        CoreModule,
        SharedModule,
        AuthModule,
    ],
    providers: [
        {
            provide: API_ERROR_KEY,
            useValue: new BehaviorSubject<null | unknown>(null),
        },
        RequestInterceptorProvider,
        ErrorInterceptorProvider,
    ],
    bootstrap: [AppComponent],
})
export class AppModule {}
