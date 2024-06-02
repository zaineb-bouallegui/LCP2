import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MendixComponent } from './mendix.component';

describe('MendixComponent', () => {
  let component: MendixComponent;
  let fixture: ComponentFixture<MendixComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MendixComponent]
    });
    fixture = TestBed.createComponent(MendixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
