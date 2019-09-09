import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VidiSmestajComponent } from './vidi-smestaj.component';

describe('VidiSmestajComponent', () => {
  let component: VidiSmestajComponent;
  let fixture: ComponentFixture<VidiSmestajComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VidiSmestajComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VidiSmestajComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
