import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListViewLoisirDiverComponent } from './list-view-loisir-diver.component';

describe('ListViewLoisirDiverComponent', () => {
  let component: ListViewLoisirDiverComponent;
  let fixture: ComponentFixture<ListViewLoisirDiverComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListViewLoisirDiverComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListViewLoisirDiverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
