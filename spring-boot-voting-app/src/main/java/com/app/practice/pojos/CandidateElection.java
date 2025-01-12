package com.app.practice.pojos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="candidate_election")
@Getter
@Setter
@NoArgsConstructor
public class CandidateElection extends Base {

	public CandidateElection(Candidate candidate, Election election) {
		
		this.candidate = candidate;
		this.election = election;
	}

	@ManyToOne
	@JoinColumn(name="candidate_id")
	private Candidate candidate;
	
	@ManyToOne
	@JoinColumn(name="election_id")
	private Election election;
	
	@OneToMany(mappedBy = "candidateElection")
	@JsonIgnore
	private List<VoterElectionCandidate> votes;
}
