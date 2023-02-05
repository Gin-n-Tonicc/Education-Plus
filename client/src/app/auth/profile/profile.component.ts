import { Component, OnInit } from '@angular/core';
import { IBusiness, IStudent } from 'src/app/shared/interfaces';
import { BusinessService } from 'src/app/shared/services/business.service';
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
    businessData: IBusiness | undefined;
    isStudent: boolean | undefined;

    constructor(
        authService: AuthService,
        studentService: StudentService,
        businessService: BusinessService
    ) {
        this.isStudent = authService.user?.role === 'STUDENT';

        if (this.isStudent) {
            studentService
                .getById(authService.user?.id as number)
                .subscribe((v) => {
                    console.log(v);

                    this.studentData = v;
                });
        } else {
            businessService
                .getById(authService.user?.id as number)
                .subscribe((v) => (this.businessData = v));
        }
    }

    ngOnInit(): void {}
}
