import { Component, Input, OnInit } from '@angular/core';
import { IBusiness } from 'src/app/shared/interfaces';

@Component({
    selector: 'app-business-card',
    templateUrl: './business-card.component.html',
    styleUrls: ['./business-card.component.css'],
})
export class BusinessCardComponent implements OnInit {
    @Input() business!: IBusiness;

    constructor() {}

    ngOnInit(): void {}
}
