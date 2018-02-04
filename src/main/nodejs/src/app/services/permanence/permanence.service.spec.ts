import { TestBed, inject } from '@angular/core/testing';

import { PermanenceService } from './permanence.service';

describe('PermanenceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PermanenceService]
    });
  });

  it('should be created', inject([PermanenceService], (service: PermanenceService) => {
    expect(service).toBeTruthy();
  }));
});
