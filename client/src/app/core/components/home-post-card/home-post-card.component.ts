import { Component, Input, OnInit } from '@angular/core';
import { IPost } from 'src/app/shared/interfaces/post.interface';

@Component({
    selector: 'app-home-post-card',
    templateUrl: './home-post-card.component.html',
    styleUrls: ['./home-post-card.component.css'],
})
export class HomePostCardComponent implements OnInit {
    @Input() post!: IPost;

    constructor() {}

    ngOnInit(): void {}
}
