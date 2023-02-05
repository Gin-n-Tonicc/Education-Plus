import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Pages } from '../shared/enums';
import { CanActivateAuth } from '../shared/guards/auth.activate';
import { CatalogComponent } from './catalog/catalog.component';
import { CreateComponent } from './create/create.component';
import { DetailsComponent } from './details/details.component';
import { SearchComponent } from './search/search.component';

const routes: Routes = [
    {
        path: Pages.Search,
        component: SearchComponent,
        data: {
            title: 'Search',
        },
    },
    {
        path: Pages.Create,
        component: CreateComponent,
        canActivate: [CanActivateAuth],
        data: {
            title: 'Create',
            reqAuth: true,
        },
    },
    {
        path: Pages.Catalog,
        component: CatalogComponent,
        data: {
            title: 'Catalog',
        },
    },
    {
        path: Pages.Details,
        component: DetailsComponent,
        data: {
            title: 'Details',
        },
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class PagesRoutingModule {}
