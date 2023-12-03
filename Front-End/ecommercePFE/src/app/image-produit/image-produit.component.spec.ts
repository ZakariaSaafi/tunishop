import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageProduitComponent } from './image-produit.component';

describe('ImageProduitComponent', () => {
  let component: ImageProduitComponent;
  let fixture: ComponentFixture<ImageProduitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ImageProduitComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ImageProduitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
