import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MeniBarComponent } from './meni-bar.component';

describe('MeniBarComponent', () => {
  let component: MeniBarComponent;
  let fixture: ComponentFixture<MeniBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MeniBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MeniBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
