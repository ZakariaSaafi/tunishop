import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListViewProduitComponent } from './list-view-produit.component';

describe('ListViewProduitComponent', () => {
  let component: ListViewProduitComponent;
  let fixture: ComponentFixture<ListViewProduitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListViewProduitComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListViewProduitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
