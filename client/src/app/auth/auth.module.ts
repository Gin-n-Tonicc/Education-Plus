import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { ProfileVisitComponent } from './profile-visit/profile-visit.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
    declarations: [
        LoginComponent,
        RegisterComponent,
        LogoutComponent,
        ProfileComponent,
        ProfileVisitComponent,
    ],
    imports: [CommonModule, ReactiveFormsModule, RouterModule],
    exports: [],
})
export class AuthModule {}
