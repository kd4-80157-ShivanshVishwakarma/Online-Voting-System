import { TestBed } from '@angular/core/testing';

import { VoterElectionCandidateService } from './voter-election-candidate.service';

describe('VoterElectionCandidateService', () => {
  let service: VoterElectionCandidateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VoterElectionCandidateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
