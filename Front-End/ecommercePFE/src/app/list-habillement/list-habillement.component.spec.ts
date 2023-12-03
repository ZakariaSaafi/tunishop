import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListHabillementComponent } from './list-habillement.component';

describe('ListHabillementComponent', () => {
  let component: ListHabillementComponent;
  let fixture: ComponentFixture<ListHabillementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListHabillementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListHabillementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
