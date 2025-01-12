import { TestBed } from '@angular/core/testing';

import { CandidateElectionService } from './candidate-election.service';

describe('CandidateElectionService', () => {
  let service: CandidateElectionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CandidateElectionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
