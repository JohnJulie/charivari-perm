import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckPermanenceComponent } from './check-permanence.component';

describe('CheckPermanenceComponent', () => {
  let component: CheckPermanenceComponent;
  let fixture: ComponentFixture<CheckPermanenceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CheckPermanenceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckPermanenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
