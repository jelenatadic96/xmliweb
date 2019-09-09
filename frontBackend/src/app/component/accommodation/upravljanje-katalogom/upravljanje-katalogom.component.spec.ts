import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpravljanjeKatalogomComponent } from './upravljanje-katalogom.component';

describe('UpravljanjeKatalogomComponent', () => {
  let component: UpravljanjeKatalogomComponent;
  let fixture: ComponentFixture<UpravljanjeKatalogomComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpravljanjeKatalogomComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpravljanjeKatalogomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
