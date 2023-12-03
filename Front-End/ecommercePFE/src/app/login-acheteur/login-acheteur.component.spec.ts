import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginAcheteurComponent } from './login-acheteur.component';

describe('LoginAcheteurComponent', () => {
  let component: LoginAcheteurComponent;
  let fixture: ComponentFixture<LoginAcheteurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginAcheteurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginAcheteurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
