import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListViewImmobilierComponent } from './list-view-immobilier.component';

describe('ListViewImmobilierComponent', () => {
  let component: ListViewImmobilierComponent;
  let fixture: ComponentFixture<ListViewImmobilierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListViewImmobilierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListViewImmobilierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
