import { TestBed } from '@angular/core/testing';

import { BloodRegistryService } from './blood-registry.service';

describe('BloodRegistryService', () => {
  let service: BloodRegistryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BloodRegistryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
