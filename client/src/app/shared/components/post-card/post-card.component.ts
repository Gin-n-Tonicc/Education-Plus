import { Component, Input, OnInit } from '@angular/core';
import { IPost } from 'src/app/shared/interfaces/post.interface';

@Component({
    selector: 'app-post-card',
    templateUrl: './post-card.component.html',
    styleUrls: ['./post-card.component.css'],
})
export class PostCardComponent implements OnInit {
    @Input() post!: IPost;

    constructor() {}

    ngOnInit(): void {}
}
