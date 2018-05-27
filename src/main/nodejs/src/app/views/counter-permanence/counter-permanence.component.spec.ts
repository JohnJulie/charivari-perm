import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CounterPermanenceComponent } from './counter-permanence.component';

describe('CounterPermanenceComponent', () => {
  let component: CounterPermanenceComponent;
  let fixture: ComponentFixture<CounterPermanenceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CounterPermanenceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CounterPermanenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
