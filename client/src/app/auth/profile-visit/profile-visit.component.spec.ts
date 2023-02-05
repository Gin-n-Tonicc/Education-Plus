import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileVisitComponent } from './profile-visit.component';

describe('ProfileVisitComponent', () => {
  let component: ProfileVisitComponent;
  let fixture: ComponentFixture<ProfileVisitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileVisitComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileVisitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
