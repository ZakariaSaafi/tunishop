import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListViewInfoMultiComponent } from './list-view-info-multi.component';

describe('ListViewInfoMultiComponent', () => {
  let component: ListViewInfoMultiComponent;
  let fixture: ComponentFixture<ListViewInfoMultiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListViewInfoMultiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListViewInfoMultiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
