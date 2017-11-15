import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DdhhComponent } from './ddhh.component';

describe('DdhhComponent', () => {
  let component: DdhhComponent;
  let fixture: ComponentFixture<DdhhComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DdhhComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DdhhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
