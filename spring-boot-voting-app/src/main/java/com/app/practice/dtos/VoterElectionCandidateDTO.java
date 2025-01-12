package com.app.practice.dtos;

import com.app.practice.pojos.CandidateElection;

import lombok.*;

@Getter
@Setter
public class VoterElectionCandidateDTO {

	private Integer electionId;
	private Integer voterId;
	private CandidateElection candidateElection;
	
	public VoterElectionCandidateDTO(Integer electionId, Integer voterId, CandidateElection candidateElection) {
		super();
		this.electionId = electionId;
		this.voterId = voterId;
		this.candidateElection = candidateElection;
	}
	
	
}
