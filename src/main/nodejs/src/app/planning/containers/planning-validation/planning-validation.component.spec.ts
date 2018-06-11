import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlanningValidationComponent } from './planning-validation.component';

describe('PlanningValidationComponent', () => {
  let component: PlanningValidationComponent;
  let fixture: ComponentFixture<PlanningValidationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlanningValidationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlanningValidationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
