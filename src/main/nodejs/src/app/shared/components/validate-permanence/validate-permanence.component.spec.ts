import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ValidatePermanenceComponent } from './validate-permanence.component';

describe('ValidatePermanenceComponent', () => {
  let component: ValidatePermanenceComponent;
  let fixture: ComponentFixture<ValidatePermanenceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ValidatePermanenceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ValidatePermanenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
