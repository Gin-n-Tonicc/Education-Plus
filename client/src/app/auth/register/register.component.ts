import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

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

    constructor(private fb: FormBuilder) {
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
    }

    ngOnInit(): void {}

    studentForm() {
        return this.fb.group({
            fullName: this.fb.control(''),
            email: this.fb.control(''),
            school: this.fb.control(''),
            town: this.fb.control(''),
            password: this.fb.control(''),
            repeatPassword: this.fb.control(''),
        });
    }

    businessForm() {
        return this.fb.group({
            name: this.fb.control(''),
            email: this.fb.control(''),
            description: this.fb.control(''),
            password: this.fb.control(''),
            repeatPassword: this.fb.control(''),
        });
    }
}
