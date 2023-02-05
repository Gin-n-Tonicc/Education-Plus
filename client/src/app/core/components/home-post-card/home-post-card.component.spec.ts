import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePostCardComponent } from './home-post-card.component';

describe('HomePostCardComponent', () => {
  let component: HomePostCardComponent;
  let fixture: ComponentFixture<HomePostCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomePostCardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomePostCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
