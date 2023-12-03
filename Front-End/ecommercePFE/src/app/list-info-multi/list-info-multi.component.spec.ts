import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListInfoMultiComponent } from './list-info-multi.component';

describe('ListInfoMultiComponent', () => {
  let component: ListInfoMultiComponent;
  let fixture: ComponentFixture<ListInfoMultiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListInfoMultiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListInfoMultiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
