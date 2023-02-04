import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root',
})
export class SpinnerService {
    counter = 0;

    constructor() {}

    reset() {
        this.counter = 0;
    }

    increment() {
        this.counter++;
    }

    decrement() {
        if (this.counter >= 1) {
            this.counter--;
        }
    }
}
