import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VoterElectionCandidateComponent } from './voter-election-candidate.component';

describe('VoterElectionCandidateComponent', () => {
  let component: VoterElectionCandidateComponent;
  let fixture: ComponentFixture<VoterElectionCandidateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VoterElectionCandidateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VoterElectionCandidateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
