import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pages } from 'src/app/shared/enums';
import { IBusiness } from 'src/app/shared/interfaces';
import { IPost } from 'src/app/shared/interfaces/post.interface';
import { BusinessService } from 'src/app/shared/services/business.service';
import { PostService } from '../services/post.service';

@Component({
    selector: 'app-details',
    templateUrl: './details.component.html',
    styleUrls: ['./details.component.css'],
})
export class DetailsComponent implements OnInit {
    postData!: IPost;
    businessData!: IBusiness;

    constructor(
        private businessService: BusinessService,
        postService: PostService,
        route: ActivatedRoute,
        router: Router
    ) {
        const id = route.snapshot.paramMap.get('id');
        if (!id || isNaN(Number(id))) {
            router.navigate([Pages.Home]);
        }

        postService.fetchById(Number(id)).subscribe({
            next: (v) => {
                this.postData = v;
                this.fetchBusinessData(v.businessId);
            },
        });
    }

    ngOnInit(): void {}

    fetchBusinessData(id: number) {
        this.businessService.getById(id).subscribe({
            next: (v) => (this.businessData = v),
        });
    }
}
