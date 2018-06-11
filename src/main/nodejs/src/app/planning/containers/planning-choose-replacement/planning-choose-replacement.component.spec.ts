import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlanningChooseReplacementComponent } from './planning-choose-replacement.component';

describe('PlanningChooseReplacementComponent', () => {
  let component: PlanningChooseReplacementComponent;
  let fixture: ComponentFixture<PlanningChooseReplacementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlanningChooseReplacementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlanningChooseReplacementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
