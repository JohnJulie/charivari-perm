import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeValidationComponent } from './home-validation.component';

describe('HomeValidationComponent', () => {
  let component: HomeValidationComponent;
  let fixture: ComponentFixture<HomeValidationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeValidationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeValidationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
