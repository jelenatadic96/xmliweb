import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VidiRezervacijeComponent } from './vidi-rezervacije.component';

describe('VidiRezervacijeComponent', () => {
  let component: VidiRezervacijeComponent;
  let fixture: ComponentFixture<VidiRezervacijeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VidiRezervacijeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VidiRezervacijeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
