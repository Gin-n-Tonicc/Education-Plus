import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Pages } from 'src/app/shared/enums';
import { AuthService } from '../services/auth.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
})
export class LoginComponent {
    form: FormGroup;
    nestedForm: FormGroup;
    currentForm: string | undefined;

    constructor(
        private fb: FormBuilder,
        private authService: AuthService,
        private router: Router
    ) {
        this.form = fb.group({
            currentForm: '',
        });

        this.nestedForm = fb.group({});

        this.form.get('currentForm')?.valueChanges.subscribe((v: string) => {
            this.currentForm = v;
            this.nestedForm = this.generateNestedForm();
        });
    }

    generateNestedForm() {
        return this.fb.group({
            email: this.fb.control('', [Validators.required, Validators.email]),
            password: this.fb.control('', [
                Validators.required,
                Validators.minLength(6),
            ]),
        });
    }

    onSubmit() {
        this.nestedForm.markAllAsTouched();
        if (this.nestedForm.invalid) return;

        const { email, password } = this.nestedForm.value;

        if (this.currentForm === 'student') {
            this.authService.loginStudent(email, password).subscribe({
                next: () => this.onSuccess(),
            });
        } else {
            this.authService.loginBusiness(email, password).subscribe({
                next: () => this.onSuccess(),
            });
        }
    }

    private onSuccess() {
        this.router.navigate([Pages.Home]);
    }
}
