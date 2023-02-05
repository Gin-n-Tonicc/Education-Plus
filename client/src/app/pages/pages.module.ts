import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BusinessCardComponent } from './components/business-card/business-card.component';
import { SearchComponent } from './search/search.component';

@NgModule({
    declarations: [SearchComponent, BusinessCardComponent],
    imports: [CommonModule, RouterModule],
})
export class PagesModule {}
