import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Pages } from '../shared/enums';
import { CanActivateAuth } from '../shared/guards/auth.activate';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { ProfileVisitComponent } from './profile-visit/profile-visit.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
    {
        path: Pages.Login,
        component: LoginComponent,
        canActivate: [CanActivateAuth],
        data: {
            title: 'Login',
            reqAuth: false,
        },
    },
    {
        path: Pages.Register,
        component: RegisterComponent,
        canActivate: [CanActivateAuth],
        data: {
            title: 'Register',
            reqAuth: false,
        },
    },
    {
        path: Pages.Profile,
        component: ProfileComponent,
        canActivate: [CanActivateAuth],
        data: {
            title: 'Profile',
            reqAuth: true,
        },
    },
    {
        path: Pages.ProfileVisit,
        component: ProfileVisitComponent,
        data: {
            title: 'Profile',
        },
    },
    {
        path: Pages.Logout,
        component: LogoutComponent,
        canActivate: [CanActivateAuth],
        data: {
            title: 'Logout',
            reqAuth: true,
        },
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class AuthRoutingModule {}
