import { TestBed } from '@angular/core/testing';

import { PorukeService } from './poruke.service';

describe('PorukeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PorukeService = TestBed.get(PorukeService);
    expect(service).toBeTruthy();
  });
});
