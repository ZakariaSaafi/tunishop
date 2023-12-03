import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RolePageBeforeLoginComponent } from './role-page-before-login.component';

describe('RolePageBeforeLoginComponent', () => {
  let component: RolePageBeforeLoginComponent;
  let fixture: ComponentFixture<RolePageBeforeLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RolePageBeforeLoginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RolePageBeforeLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
