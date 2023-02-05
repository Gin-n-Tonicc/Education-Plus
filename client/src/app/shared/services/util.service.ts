import { Injectable } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Injectable({
    providedIn: 'root',
})
export class UtilService {
    constructor(private router: Router) {}

    updateQueryParams(route: ActivatedRoute, queryParams: Params) {
        this.router.navigate([], {
            relativeTo: route,
            queryParams,
            queryParamsHandling: 'merge',
        });
    }
}
