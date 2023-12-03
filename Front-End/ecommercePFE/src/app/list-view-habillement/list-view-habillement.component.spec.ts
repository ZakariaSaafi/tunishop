import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListViewHabillementComponent } from './list-view-habillement.component';

describe('ListViewHabillementComponent', () => {
  let component: ListViewHabillementComponent;
  let fixture: ComponentFixture<ListViewHabillementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListViewHabillementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListViewHabillementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
