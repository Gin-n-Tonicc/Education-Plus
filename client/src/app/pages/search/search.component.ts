import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IBusiness } from 'src/app/shared/interfaces';
import { BusinessService } from 'src/app/shared/services/business.service';

@Component({
    selector: 'app-search',
    templateUrl: './search.component.html',
    styleUrls: ['./search.component.css'],
})
export class SearchComponent implements OnInit {
    businesses$: Observable<IBusiness[]> | undefined;

    constructor(
        private route: ActivatedRoute,
        private businessService: BusinessService
    ) {}

    ngOnInit(): void {
        this.route.queryParams.subscribe((params) => {
            this.businesses$ = this.businessService.getBySearch(
                params['search'] || ''
            );
        });
    }
}
