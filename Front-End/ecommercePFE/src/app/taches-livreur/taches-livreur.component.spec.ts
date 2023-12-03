import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TachesLivreurComponent } from './taches-livreur.component';

describe('TachesLivreurComponent', () => {
  let component: TachesLivreurComponent;
  let fixture: ComponentFixture<TachesLivreurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TachesLivreurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TachesLivreurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
