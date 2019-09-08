import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SrediCeneComponent } from './sredi-cene.component';

describe('SrediCeneComponent', () => {
  let component: SrediCeneComponent;
  let fixture: ComponentFixture<SrediCeneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SrediCeneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SrediCeneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
