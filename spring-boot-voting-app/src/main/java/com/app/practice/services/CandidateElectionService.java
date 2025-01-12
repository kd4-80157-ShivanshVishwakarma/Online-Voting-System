package com.app.practice.services;

import java.util.List;

import com.app.practice.dtos.CandidateElectionDTO;
import com.app.practice.dtos.CandidateStatsDTO;
import com.app.practice.pojos.CandidateElection;

public interface CandidateElectionService {

	List<CandidateElection> getAll();
	String create(CandidateElectionDTO ce);
}

