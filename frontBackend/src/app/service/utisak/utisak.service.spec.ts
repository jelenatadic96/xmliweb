import { TestBed } from '@angular/core/testing';

import { UtisakService } from './utisak.service';

describe('UtisakService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UtisakService = TestBed.get(UtisakService);
    expect(service).toBeTruthy();
  });
});
