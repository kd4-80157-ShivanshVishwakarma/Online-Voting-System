package com.app.practice.dtos;


import lombok.*;

@Getter
@Setter
public class VoterElectionCandidateRDTO {

	private Integer electionId;
	private Integer voterId;
	private Integer candidateElectionId;
	
	public VoterElectionCandidateRDTO(Integer electionId, Integer voterId, Integer candidateElectionId) {
		super();
		this.electionId = electionId;
		this.voterId = voterId;
		this.candidateElectionId = candidateElectionId;
	}
	
}
