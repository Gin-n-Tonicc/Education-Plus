import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Pages } from 'src/app/shared/enums';
import { UtilService } from 'src/app/shared/services/util.service';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
    form = this.fb.group({
        search: this.fb.control(''),
    });

    get isAuthenticated() {
        return this.authService.isUserAuthenticated;
    }

    get isBusiness() {
        return (
            this.isAuthenticated && this.authService.user?.role === 'BUSINESS'
        );
    }

    constructor(
        private authService: AuthService,
        private router: Router,
        private route: ActivatedRoute,
        private utilService: UtilService,
        private fb: FormBuilder
    ) {}

    ngOnInit(): void {}

    handleSearch() {
        const search = this.form.value.search;
        const params: Params = { search: search };

        if (this.router.url.includes(Pages.Search)) {
            this.utilService.updateQueryParams(this.route, params);
        } else {
            this.router.navigate([Pages.Search], {
                queryParams: params,
            });
        }
    }
}
