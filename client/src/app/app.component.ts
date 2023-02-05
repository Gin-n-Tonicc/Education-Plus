import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivationStart, Router } from '@angular/router';
import { filter, map } from 'rxjs';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
})
export class AppComponent {
    title = 'client';

    constructor(private router: Router, private titleService: Title) {
        this.router.events
            .pipe(
                filter(
                    (e): e is ActivationStart => e instanceof ActivationStart
                ),
                map((e) => e.snapshot.data?.['title']),
                filter((d) => !!d)
            )
            .subscribe((pageTitle) => {
                this.titleService.setTitle(pageTitle);
            });
    }
}
