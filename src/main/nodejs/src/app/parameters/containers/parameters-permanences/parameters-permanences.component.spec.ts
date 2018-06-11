import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParametersPermanencesComponent } from './parameters-permanences.component';

describe('ParametersPermanencesComponent', () => {
  let component: ParametersPermanencesComponent;
  let fixture: ComponentFixture<ParametersPermanencesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParametersPermanencesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParametersPermanencesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
