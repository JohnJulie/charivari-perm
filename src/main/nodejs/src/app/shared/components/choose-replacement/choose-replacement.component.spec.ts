import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseReplacementComponent } from './choose-replacement.component';

describe('ChooseReplacementComponent', () => {
  let component: ChooseReplacementComponent;
  let fixture: ComponentFixture<ChooseReplacementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChooseReplacementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChooseReplacementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
