import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { IPost } from 'src/app/shared/interfaces/post.interface';
import { PostService } from '../services/post.service';

@Component({
    selector: 'app-catalog',
    templateUrl: './catalog.component.html',
    styleUrls: ['./catalog.component.css'],
})
export class CatalogComponent {
    posts$!: Observable<IPost[]>;

    constructor(private postService: PostService) {
        this.posts$ = postService.fetchPosts();
    }
}
