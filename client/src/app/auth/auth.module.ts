import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';

@NgModule({
    declarations: [LoginComponent, RegisterComponent, LogoutComponent, ProfileComponent],
    imports: [
        HttpClientModule,
        CommonModule,
        ReactiveFormsModule,
        RouterModule,
    ],
    exports: [],
})
export class AuthModule {}
