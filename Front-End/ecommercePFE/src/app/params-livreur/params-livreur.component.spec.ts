import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamsLivreurComponent } from './params-livreur.component';

describe('ParamsLivreurComponent', () => {
  let component: ParamsLivreurComponent;
  let fixture: ComponentFixture<ParamsLivreurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParamsLivreurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamsLivreurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
