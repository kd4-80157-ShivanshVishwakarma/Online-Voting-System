import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidateElectionComponent } from './candidate-election.component';

describe('CandidateElectionComponent', () => {
  let component: CandidateElectionComponent;
  let fixture: ComponentFixture<CandidateElectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CandidateElectionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CandidateElectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
