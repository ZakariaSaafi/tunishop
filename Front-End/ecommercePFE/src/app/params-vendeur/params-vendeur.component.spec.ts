import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamsVendeurComponent } from './params-vendeur.component';

describe('ParamsVendeurComponent', () => {
  let component: ParamsVendeurComponent;
  let fixture: ComponentFixture<ParamsVendeurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamsVendeurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamsVendeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
