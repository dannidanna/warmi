import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DelitosComponent } from './delitos.component';

describe('DelitosComponent', () => {
  let component: DelitosComponent;
  let fixture: ComponentFixture<DelitosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DelitosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DelitosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
