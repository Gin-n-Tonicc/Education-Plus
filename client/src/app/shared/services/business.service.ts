import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IBusiness } from 'src/app/shared/interfaces';

@Injectable({
    providedIn: 'root',
})
export class BusinessService {
    constructor(private httpClient: HttpClient) {}

    getById(id: number) {
        return this.httpClient.get<IBusiness>(`api/businesses/${id}`);
    }

    getBySearch(search: string) {
        return this.httpClient.get<IBusiness[]>(
            `api/businesses/search/${search}`
        );
    }
}
