import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardLivreurComponent } from './dashboard-livreur.component';

describe('DashboardLivreurComponent', () => {
  let component: DashboardLivreurComponent;
  let fixture: ComponentFixture<DashboardLivreurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardLivreurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardLivreurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
