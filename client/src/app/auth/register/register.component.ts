import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Pages } from 'src/app/shared/enums';
import { sameValueGroupValidator } from 'src/app/shared/validators/same-value-validator';
import { AuthService } from '../services/auth.service';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
    readonly availableForms = {
        student: this.studentForm,
        business: this.businessForm,
    };

    form: FormGroup;
    currentNestedForm: FormGroup;
    currentForm: undefined | string;

    constructor(
        private fb: FormBuilder,
        private authService: AuthService,
        private router: Router
    ) {
        this.form = fb.group({
            currentForm: fb.control(''),
        });

        this.currentNestedForm = fb.group({});

        this.form.get('currentForm')?.valueChanges.subscribe((v: string) => {
            this.currentForm = v;

            this.currentNestedForm =
                v === 'student' ? this.studentForm() : this.businessForm();

            console.log(this.currentNestedForm);
        });

        console.log(this.currentNestedForm.get('email')?.getError('email'));
    }

    ngOnInit(): void {}

    studentForm() {
        return this.fb.group({
            fullName: this.fb.control('', [
                Validators.required,
                Validators.minLength(2),
            ]),
            email: this.fb.control('', [Validators.required, Validators.email]),
            age: this.fb.control('', [Validators.required, Validators.min(14)]),
            school: this.fb.control('', [Validators.required]),
            town: this.fb.control('', [Validators.required]),
            interests: this.fb.control('', [Validators.required]),
            passwords: this.fb.group(
                {
                    password: this.fb.control('', [
                        Validators.required,
                        Validators.minLength(6),
                    ]),

                    rePassword: this.fb.control(''),
                },
                {
                    validators: [
                        sameValueGroupValidator('password', 'rePassword'),
                    ],
                }
            ),
        });
    }

    businessForm() {
        return this.fb.group({
            name: this.fb.control('', [
                Validators.required,
                Validators.minLength(2),
            ]),
            email: this.fb.control('', [Validators.required, Validators.email]),
            description: this.fb.control('', [
                Validators.required,
                Validators.minLength(2),
            ]),
            placeOfResidence: this.fb.control('', [Validators.required]),
            passwords: this.fb.group(
                {
                    password: this.fb.control('', [
                        Validators.required,
                        Validators.minLength(6),
                    ]),

                    rePassword: this.fb.control(''),
                },
                {
                    validators: [
                        sameValueGroupValidator('password', 'rePassword'),
                    ],
                }
            ),
        });
    }

    onSubmit() {
        this.currentNestedForm.markAllAsTouched();

        if (!this.currentNestedForm.valid) {
            return;
        }

        const { passwords, ...restOfData } = this.currentNestedForm.value;
        restOfData['password'] = passwords.password;

        if (this.currentForm === 'student') {
            this.authService.registerStudent(restOfData).subscribe({
                next: (v) => this.onSuccess(),
            });
        } else {
            this.authService.registerBusiness(restOfData).subscribe({
                next: (v) => this.onSuccess(),
            });
        }
    }

    private onSuccess() {
        this.router.navigate([Pages.Home]);
    }
}
