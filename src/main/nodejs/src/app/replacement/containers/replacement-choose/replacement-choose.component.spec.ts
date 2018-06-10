import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReplacementChooseComponent } from './replacement-choose.component';

describe('ReplacementChooseComponent', () => {
  let component: ReplacementChooseComponent;
  let fixture: ComponentFixture<ReplacementChooseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReplacementChooseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReplacementChooseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
