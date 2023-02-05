import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IFollow } from '../interfaces/follow.interface';

@Injectable({
    providedIn: 'root',
})
export class FollowService {
    constructor(private httpClient: HttpClient) {}

    getFollows(userId: number) {
        return this.httpClient.get<IFollow[]>(`api/followers/${userId}`);
    }

    createFollow(userId: number, businessId: number) {
        return this.httpClient.post('api/followers/create', {
            studentId: userId,
            businessId,
        });
    }

    deleteFollow(userId: number, businessId: number) {
        return this.httpClient.delete('api/followers/delete', {
            body: { studentId: userId, businessId },
        });
    }
}
