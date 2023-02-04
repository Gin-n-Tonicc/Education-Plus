import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './core/home/home.component';
import { Pages } from './shared/enums';

const routes: Routes = [
    {
        path: '',
        pathMatch: 'full',
        redirectTo: Pages.Home,
    },
    {
        path: Pages.Home,
        component: HomeComponent,
        data: {
            title: 'Home',
        },
    },
    //   {
    //     path: Pages.NotFound,
    //     component: NotFoundComponent,
    //     data: {
    //       title: 'Not Found',
    //     },
    //   },
    {
        path: '',
        loadChildren: () =>
            import('./auth/auth-routing.module').then(
                (m) => m.AuthRoutingModule
            ),
    },
    {
        path: '**',
        redirectTo: Pages.NotFound,
    },
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules }),
    ],
    exports: [RouterModule],
})
export class AppRoutingModule {}
