package com.app.practice.pojos;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="voter_election_candidate")
@Getter
@Setter
@NoArgsConstructor
public class VoterElectionCandidate extends Base{

	
	
	public VoterElectionCandidate(Election election, Voter voter, CandidateElection candidateElection) {
		super();
		this.election = election;
		this.voter = voter;
		this.candidateElection = candidateElection;
	}

	@ManyToOne
	@JoinColumn(name="election_id")
	private Election election;
	
	@ManyToOne
	@JoinColumn(name="voter_id")
	private Voter voter;
	
	@ManyToOne
	@JoinColumn(name="candidate_election_id")
	private CandidateElection candidateElection;
}
