import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListLoisirDiverComponent } from './list-loisir-diver.component';

describe('ListLoisirDiverComponent', () => {
  let component: ListLoisirDiverComponent;
  let fixture: ComponentFixture<ListLoisirDiverComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListLoisirDiverComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListLoisirDiverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
