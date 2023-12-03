import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListProduitVendeurComponent } from './list-produit-vendeur.component';

describe('ListProduitVendeurComponent', () => {
  let component: ListProduitVendeurComponent;
  let fixture: ComponentFixture<ListProduitVendeurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListProduitVendeurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListProduitVendeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
