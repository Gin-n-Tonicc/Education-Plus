import { Component, Inject, OnDestroy } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { API_ERROR_KEY } from 'src/app/shared/constants';

@Component({
    selector: 'app-error',
    templateUrl: './error.component.html',
    styleUrls: ['./error.component.css'],
})
export class ErrorComponent implements OnDestroy {
    readonly apiError$ = this.apiError$$.asObservable();
    error: string | null = '';
    timeoutId!: number;
    counter = 0;

    constructor(
        @Inject(API_ERROR_KEY)
        private apiError$$: BehaviorSubject<null | string>
    ) {
        this.apiError$.subscribe((error: string | null) => {
            this.error = error;

            if (error) {
                this.counter++;

                this.timeoutId = setTimeout(() => {
                    this.counter--;

                    if (!this.counter) {
                        this.apiError$$.next(null);
                    }
                }, 5000) as unknown as number;
            }
        });
    }

    ngOnDestroy(): void {
        this.clearError();
    }

    clearError(): void {
        if (this.timeoutId) {
            clearTimeout(this.timeoutId);
            this.apiError$$.next(null);
        }
    }
}
