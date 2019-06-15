import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ValidationsPermanencesComponent } from './validations-permanences.component';

describe('ReplacementPermanencesComponent', () => {
  let component: ValidationsPermanencesComponent;
  let fixture: ComponentFixture<ValidationsPermanencesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ValidationsPermanencesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ValidationsPermanencesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
