import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { API_ERROR_KEY } from 'src/app/shared/constants';
import { Pages } from 'src/app/shared/enums';
import { PostService } from '../services/post.service';

@Component({
    selector: 'app-create',
    templateUrl: './create.component.html',
    styleUrls: ['./create.component.css'],
})
export class CreateComponent implements OnInit {
    form: FormGroup;

    constructor(
        private fb: FormBuilder,
        @Inject(API_ERROR_KEY)
        private apiError$$: BehaviorSubject<string | null>,
        private postService: PostService,
        private router: Router
    ) {
        this.form = this.constructForm();
    }

    ngOnInit(): void {}

    constructForm() {
        return this.fb.group({
            description: this.fb.control('', [Validators.required]),
            category: this.fb.control('', [Validators.required]),
            interests: this.fb.control('', [Validators.required]),
            ageGroup: this.fb.control('', [Validators.required]),
            requirements: this.fb.control('', [Validators.required]),
            linkToApplicationForm: this.fb.control('', [Validators.required]),
            place: this.fb.control('', [Validators.required]),
            deadlineToParticipate: this.fb.control('', [Validators.required]),
            startDate: this.fb.control('', [Validators.required]),
            endDate: this.fb.control('', [Validators.required]),
        });
    }

    onSubmit() {
        this.form.markAllAsTouched();
        console.log(this.form.value);

        if (this.form.invalid) return;

        const { startDate, endDate } = this.form.value;

        if (Date.parse(startDate) > Date.parse(endDate)) {
            this.apiError$$.next('End date must be greater than start date!');
            return;
        }

        this.postService.createPost(this.form.value).subscribe({
            next: () => this.router.navigate([Pages.Home]),
        });
    }
}
