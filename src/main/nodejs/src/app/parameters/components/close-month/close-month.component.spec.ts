import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CloseMonthComponent } from './close-month.component';

describe('CloseMonthComponent', () => {
  let component: CloseMonthComponent;
  let fixture: ComponentFixture<CloseMonthComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CloseMonthComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CloseMonthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
