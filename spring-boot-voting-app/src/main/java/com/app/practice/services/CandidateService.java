package com.app.practice.services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.app.practice.dtos.CandidateDTO;
import com.app.practice.dtos.CandidateStatsDTO;
import com.app.practice.pojos.Candidate;

public interface CandidateService {

	Candidate create(CandidateDTO cdto);
	List<Candidate> getAll();
	String update(CandidateDTO cdto,Integer id);
	String remove(Integer id);
	List<CandidateStatsDTO> candidateStatsByElectionId(Integer electionId);
	
}
