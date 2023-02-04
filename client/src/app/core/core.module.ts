import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';

@NgModule({
    declarations: [HeaderComponent, HomeComponent],
    imports: [NgbAlertModule, CommonModule, RouterModule],
    exports: [HeaderComponent, HomeComponent],
})
export class CoreModule {}
