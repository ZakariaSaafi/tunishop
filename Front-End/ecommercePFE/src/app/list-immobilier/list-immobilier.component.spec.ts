import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListImmobilierComponent } from './list-immobilier.component';

describe('ListImmobilierComponent', () => {
  let component: ListImmobilierComponent;
  let fixture: ComponentFixture<ListImmobilierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListImmobilierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListImmobilierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
