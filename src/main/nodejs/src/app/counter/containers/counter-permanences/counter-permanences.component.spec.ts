import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CounterPermanencesComponent } from './counter-permanences.component';

describe('CounterPermanencesComponent', () => {
  let component: CounterPermanencesComponent;
  let fixture: ComponentFixture<CounterPermanencesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CounterPermanencesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CounterPermanencesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
