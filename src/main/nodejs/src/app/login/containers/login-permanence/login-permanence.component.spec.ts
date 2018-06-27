import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginPermanenceComponent } from './login-permanence.component';

describe('LoginPermanenceComponent', () => {
  let component: LoginPermanenceComponent;
  let fixture: ComponentFixture<LoginPermanenceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginPermanenceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginPermanenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
