import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VidiKomentareComponent } from './vidi-komentare.component';

describe('VidiKomentareComponent', () => {
  let component: VidiKomentareComponent;
  let fixture: ComponentFixture<VidiKomentareComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VidiKomentareComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VidiKomentareComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
