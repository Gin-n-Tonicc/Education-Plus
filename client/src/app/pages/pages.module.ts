import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { CatalogComponent } from './catalog/catalog.component';
import { BusinessCardComponent } from './components/business-card/business-card.component';
import { CreateComponent } from './create/create.component';
import { DetailsComponent } from './details/details.component';
import { SearchComponent } from './search/search.component';

@NgModule({
    declarations: [
        SearchComponent,
        BusinessCardComponent,
        CreateComponent,
        CatalogComponent,
        DetailsComponent,
    ],
    imports: [CommonModule, RouterModule, ReactiveFormsModule, SharedModule],
})
export class PagesModule {}
