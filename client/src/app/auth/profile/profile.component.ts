import { Component, OnInit } from '@angular/core';
import { IBusiness, IStudent } from 'src/app/shared/interfaces';
import { IFollow } from 'src/app/shared/interfaces/follow.interface';
import { BusinessService } from 'src/app/shared/services/business.service';
import { FollowService } from 'src/app/shared/services/follow.service';
import { StudentService } from 'src/app/shared/services/student.service';
import { AuthService } from '../services/auth.service';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
    editing = false;
    studentData: IStudent | undefined;
    followedBusinesses: IFollow[] | undefined;
    businessData: IBusiness | undefined;
    isStudent: boolean | undefined;

    constructor(
        private authService: AuthService,
        private followService: FollowService,
        studentService: StudentService,
        businessService: BusinessService
    ) {
        this.isStudent = authService.user?.role === 'STUDENT';

        if (this.isStudent) {
            studentService
                .getById(authService.user?.id as number)
                .subscribe((v) => (this.studentData = v));

            this.setFollows();
        } else {
            businessService
                .getById(authService.user?.id as number)
                .subscribe((v) => (this.businessData = v));
        }
    }

    ngOnInit(): void {}

    handleDelete() {
        this.setFollows();
    }

    private setFollows() {
        this.followService
            .getFollows(this.authService.user?.id as number)
            .subscribe((v) => (this.followedBusinesses = v));
    }
}
