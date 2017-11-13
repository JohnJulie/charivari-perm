import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReplacementPermanencesComponent } from './replacement-permanences.component';

describe('ReplacementPermanencesComponent', () => {
  let component: ReplacementPermanencesComponent;
  let fixture: ComponentFixture<ReplacementPermanencesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReplacementPermanencesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReplacementPermanencesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
