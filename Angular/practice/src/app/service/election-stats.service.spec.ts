import { TestBed } from '@angular/core/testing';

import { ElectionStatsService } from './election-stats.service';

describe('ElectionStatsService', () => {
  let service: ElectionStatsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ElectionStatsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
