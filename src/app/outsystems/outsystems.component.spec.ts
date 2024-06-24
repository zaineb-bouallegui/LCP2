import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OutsystemsComponent } from './outsystems.component';

describe('OutsystemsComponent', () => {
  let component: OutsystemsComponent;
  let fixture: ComponentFixture<OutsystemsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OutsystemsComponent]
    });
    fixture = TestBed.createComponent(OutsystemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
