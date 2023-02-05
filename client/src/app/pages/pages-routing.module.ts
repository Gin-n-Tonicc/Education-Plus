import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Pages } from '../shared/enums';
import { SearchComponent } from './search/search.component';

const routes: Routes = [
    {
        path: Pages.Search,
        component: SearchComponent,
        data: {
            title: 'Search',
        },
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class PagesRoutingModule {}
