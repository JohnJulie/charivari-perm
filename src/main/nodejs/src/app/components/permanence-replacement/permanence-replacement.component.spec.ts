import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PermanenceReplacementComponent } from './permanence-replacement.component';

describe('PermanenceReplacementComponent', () => {
  let component: PermanenceReplacementComponent;
  let fixture: ComponentFixture<PermanenceReplacementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PermanenceReplacementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PermanenceReplacementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
