import { TestBed } from '@angular/core/testing';

import { ProjectTrackingService } from './project-tracking.service';

describe('ProjectTrackingService', () => {
  let service: ProjectTrackingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProjectTrackingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
