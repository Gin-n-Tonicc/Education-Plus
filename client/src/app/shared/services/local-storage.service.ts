import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root',
})
export class LocalStorageService {
    constructor() {}

    checkForValue(key: string, json = false) {
        const item = this.getData(key);
        const parsed = json ? (item ? JSON.parse(item) : item) : item;

        if (parsed) {
            this.setData(key, parsed);
        }

        return parsed;
    }

    getData(key: string) {
        return localStorage.getItem(key);
    }

    setData(key: string, data: any) {
        return localStorage.setItem(key, JSON.stringify(data));
    }

    removeData(key: string) {
        return localStorage.removeItem(key);
    }
}
