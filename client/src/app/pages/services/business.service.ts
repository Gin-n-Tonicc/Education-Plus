import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IBusiness } from 'src/app/shared/interfaces';

@Injectable({
    providedIn: 'root',
})
export class BusinessService {
    constructor(private httpClient: HttpClient) {}

    getBySearch(search: string) {
        return this.httpClient.get<IBusiness[]>(
            `api/businesses/search/${search}`
        );
    }
}
