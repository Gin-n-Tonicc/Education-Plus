import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IPost } from 'src/app/shared/interfaces/post.interface';

@Injectable({
    providedIn: 'root',
})
export class PostService {
    constructor(private httpClient: HttpClient) {}

    fetchPosts() {
        return this.httpClient.get<IPost[]>('api/posts');
    }

    fetchRecent() {
        return this.httpClient.get<IPost[]>('api/posts/recent');
    }

    fetchRecentById(id: number) {
        return this.httpClient.get<IPost[]>(`api/posts/recent/${id}`);
    }

    fetchById(id: number) {
        return this.httpClient.get<IPost>(`api/posts/${id}`);
    }

    createPost(postData: Omit<IPost, 'businessId'>) {
        return this.httpClient.post<IPost>('api/posts/create', postData);
    }
}
