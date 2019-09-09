import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DodavanjeSmestajaComponent } from './dodavanje-smestaja.component';

describe('DodavanjeSmestajaComponent', () => {
  let component: DodavanjeSmestajaComponent;
  let fixture: ComponentFixture<DodavanjeSmestajaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DodavanjeSmestajaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DodavanjeSmestajaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
