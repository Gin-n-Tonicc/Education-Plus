import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { PostService } from 'src/app/pages/services/post.service';
import { IPost } from 'src/app/shared/interfaces/post.interface';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
    recentPosts$!: Observable<IPost[]>;

    constructor(postService: PostService) {
        this.recentPosts$ = postService.fetchRecent();
    }

    ngOnInit(): void {}
}
