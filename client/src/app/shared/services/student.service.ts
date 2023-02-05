import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IStudent } from 'src/app/shared/interfaces';

@Injectable({
    providedIn: 'root',
})
export class StudentService {
    constructor(private httpClient: HttpClient) {}

    getById(id: number) {
        return this.httpClient.get<IStudent>(`api/students/${id}`);
    }
}
