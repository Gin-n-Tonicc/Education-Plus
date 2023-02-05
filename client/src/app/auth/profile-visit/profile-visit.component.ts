import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { PostService } from 'src/app/pages/services/post.service';
import { Pages } from 'src/app/shared/enums';
import { IBusiness } from 'src/app/shared/interfaces';
import { IPost } from 'src/app/shared/interfaces/post.interface';
import { BusinessService } from 'src/app/shared/services/business.service';
import { FollowService } from 'src/app/shared/services/follow.service';
import { AuthService } from '../services/auth.service';

@Component({
    selector: 'app-profile-visit',
    templateUrl: './profile-visit.component.html',
    styleUrls: ['./profile-visit.component.css'],
})
export class ProfileVisitComponent implements OnInit {
    get isAuthenticated() {
        return this.authService.isUserAuthenticated;
    }

    businessData: IBusiness | undefined;
    recentPosts$!: Observable<IPost[]>;
    submitted = false;
    isStudent = false;

    constructor(
        private followService: FollowService,
        private authService: AuthService,
        postService: PostService,
        router: Router,
        businessService: BusinessService,
        route: ActivatedRoute
    ) {
        const id = route.snapshot.paramMap.get('id');
        this.isStudent = authService.user?.role === 'STUDENT';

        this.recentPosts$ = postService.fetchRecentById(
            Number(this.authService.user?.id)
        );

        if (!id || isNaN(Number(id))) {
            router.navigate([Pages.Home]);
            return;
        }

        businessService.getById(Number(id)).subscribe({
            next: (v) => (this.businessData = v),
            error: (v) => router.navigate([Pages.Home]),
        });
    }

    ngOnInit(): void {}

    handleFollow() {
        if (!this.isAuthenticated) return;
        this.submitted = true;

        this.followService
            .createFollow(
                this.authService.user?.id as number,
                this.businessData?.id as number
            )
            .subscribe({
                next: () => (this.submitted = false),
                error: () => (this.submitted = false),
            });
    }
}
