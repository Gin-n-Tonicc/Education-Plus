import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
    selector: 'app-authenticate',
    templateUrl: './authenticate.component.html',
    styleUrls: ['./authenticate.component.css'],
})
export class AuthenticateComponent {
    authenticating = true;

    constructor(authService: AuthService, private router: Router) {
        authService.authenticate().subscribe({
            next: () => this.onEnd(),
            error: () => this.onEnd(),
        });
    }

    private onEnd() {
        this.authenticating = false;
    }
}
