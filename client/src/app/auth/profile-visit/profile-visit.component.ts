import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pages } from 'src/app/shared/enums';
import { IBusiness } from 'src/app/shared/interfaces';
import { BusinessService } from 'src/app/shared/services/business.service';

@Component({
    selector: 'app-profile-visit',
    templateUrl: './profile-visit.component.html',
    styleUrls: ['./profile-visit.component.css'],
})
export class ProfileVisitComponent implements OnInit {
    businessData: IBusiness | undefined;

    constructor(
        router: Router,
        businessService: BusinessService,
        route: ActivatedRoute
    ) {
        const id = route.snapshot.paramMap.get('id');

        if (!id || isNaN(Number(id))) {
            router.navigate([Pages.Home]);
            return;
        }

        businessService
            .getById(Number(id))
            .subscribe({
                next: (v) => (this.businessData = v),
                error: (v) => router.navigate([Pages.Home]),
            });
    }

    ngOnInit(): void {}
}
