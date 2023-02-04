import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { SpinnerService } from './spinner.service';

@Injectable({
    providedIn: 'root',
})
export class HttpCancelService {
    private pendingHTTPRequests$$ = new Subject<void>();

    constructor(spinnerService: SpinnerService) {
        this.pendingHTTPRequests$$.subscribe(() => spinnerService.reset());
    }

    cancelPendingRequests() {
        this.pendingHTTPRequests$$.next();
    }

    onCancelPendingRequests() {
        return this.pendingHTTPRequests$$.asObservable();
    }
}
