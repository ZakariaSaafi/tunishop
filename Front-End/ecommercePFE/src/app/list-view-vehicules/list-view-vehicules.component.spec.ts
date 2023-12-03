import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListViewVehiculesComponent } from './list-view-vehicules.component';

describe('ListViewVehiculesComponent', () => {
  let component: ListViewVehiculesComponent;
  let fixture: ComponentFixture<ListViewVehiculesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListViewVehiculesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListViewVehiculesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
