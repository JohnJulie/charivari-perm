import { TestBed, inject } from '@angular/core/testing';

import { CurrentStateService } from './current-state.service';

describe('CurrentStateService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CurrentStateService]
    });
  });

  it('should be created', inject([CurrentStateService], (service: CurrentStateService) => {
    expect(service).toBeTruthy();
  }));
});
