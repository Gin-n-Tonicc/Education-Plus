import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { ErrorComponent } from './error/error.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';

@NgModule({
    declarations: [HeaderComponent, HomeComponent, ErrorComponent],
    imports: [NgbAlertModule, CommonModule, RouterModule],
    exports: [HeaderComponent, HomeComponent, ErrorComponent],
})
export class CoreModule {}
