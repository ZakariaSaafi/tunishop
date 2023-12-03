import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MdpRecoveryComponent } from './mdp-recovery.component';

describe('MdpRecoveryComponent', () => {
  let component: MdpRecoveryComponent;
  let fixture: ComponentFixture<MdpRecoveryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MdpRecoveryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MdpRecoveryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
