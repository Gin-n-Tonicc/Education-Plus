import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { AuthenticateComponent } from './authenticate/authenticate.component';
import { ErrorComponent } from './error/error.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { HomePostCardComponent } from './components/home-post-card/home-post-card.component';
import { NotFoundComponent } from './not-found/not-found.component';

@NgModule({
    declarations: [
        HeaderComponent,
        HomeComponent,
        ErrorComponent,
        AuthenticateComponent,
        HomePostCardComponent,
        NotFoundComponent,
    ],
    imports: [NgbAlertModule, CommonModule, RouterModule, ReactiveFormsModule],
    exports: [
        HeaderComponent,
        HomeComponent,
        ErrorComponent,
        AuthenticateComponent,
    ],
})
export class CoreModule {}
