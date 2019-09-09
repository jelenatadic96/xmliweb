import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAccommodationComponent } from './view-accommodation.component';

describe('ViewAccommodationComponent', () => {
  let component: ViewAccommodationComponent;
  let fixture: ComponentFixture<ViewAccommodationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAccommodationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAccommodationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
