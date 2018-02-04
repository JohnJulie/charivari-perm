import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlanningPermanenceComponent } from './planning-permanence.component';

describe('PlanningPermanenceComponent', () => {
  let component: PlanningPermanenceComponent;
  let fixture: ComponentFixture<PlanningPermanenceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlanningPermanenceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlanningPermanenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
