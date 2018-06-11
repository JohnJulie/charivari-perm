import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeChooseReplacementComponent } from './home-choose-replacement.component';

describe('HomeChooseReplacementComponent', () => {
  let component: HomeChooseReplacementComponent;
  let fixture: ComponentFixture<HomeChooseReplacementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeChooseReplacementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeChooseReplacementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
