import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FollowingCardComponent } from './following-card.component';

describe('FollowingCardComponent', () => {
  let component: FollowingCardComponent;
  let fixture: ComponentFixture<FollowingCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FollowingCardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FollowingCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
