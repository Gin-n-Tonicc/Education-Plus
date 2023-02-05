import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { IBusiness } from 'src/app/shared/interfaces';
import { FollowService } from 'src/app/shared/services/follow.service';
import { AuthService } from '../../services/auth.service';

@Component({
    selector: 'app-following-card',
    templateUrl: './following-card.component.html',
    styleUrls: ['./following-card.component.css'],
})
export class FollowingCardComponent implements OnInit {
    @Input() business!: IBusiness;
    @Output() deleteEvent = new EventEmitter();

    constructor(
        private followService: FollowService,
        private authService: AuthService
    ) {}

    ngOnInit(): void {}

    onRemove() {
        this.followService
            .deleteFollow(this.authService.user?.id as number, this.business.id)
            .subscribe({ next: () => this.deleteEvent.emit() });
    }
}
