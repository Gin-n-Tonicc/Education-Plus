import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Pages } from '../shared/enums';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
    {
        path: Pages.Login,
        component: LoginComponent,
        data: {
            title: 'Login',
        },
    },
    {
        path: Pages.Register,
        component: RegisterComponent,
        data: {
            title: 'Register',
        },
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class AuthRoutingModule {}
