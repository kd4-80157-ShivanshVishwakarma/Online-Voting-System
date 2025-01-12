package com.app.practice.services;

import java.util.List;

import com.app.practice.dtos.VoteDTO;
import com.app.practice.dtos.VoterElectionCandidateDTO;
import com.app.practice.dtos.VoterElectionCandidateRDTO;
import com.app.practice.pojos.Candidate;
import com.app.practice.pojos.CandidateElection;

public interface VoterElectionCandidateService {

	List<VoterElectionCandidateDTO> getAll();
	String saveVEC(VoterElectionCandidateRDTO vecdto);
	List<CandidateElection> getCandidateElectionsByEid(Integer id);
	List<VoteDTO> getAllVotes();
}
