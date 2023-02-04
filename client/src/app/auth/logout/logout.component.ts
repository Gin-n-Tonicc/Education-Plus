import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Pages } from 'src/app/shared/enums';
import { AuthService } from '../services/auth.service';

@Component({
    selector: 'app-logout',
    templateUrl: './logout.component.html',
    styleUrls: ['./logout.component.css'],
})
export class LogoutComponent {
    constructor(authService: AuthService, router: Router) {
        authService.logout();
        router.navigate([Pages.Home]);
    }
}
