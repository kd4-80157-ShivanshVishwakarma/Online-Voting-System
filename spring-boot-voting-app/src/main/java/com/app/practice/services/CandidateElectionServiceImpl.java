package com.app.practice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.practice.dtos.CandidateElectionDTO;
import com.app.practice.pojos.Candidate;
import com.app.practice.pojos.CandidateElection;
import com.app.practice.pojos.Election;
import com.app.practice.repositories.CandidateElectionRepository;
import com.app.practice.repositories.CandidateRepository;
import com.app.practice.repositories.ElectionRepository;

@Service
@Transactional
public class CandidateElectionServiceImpl implements CandidateElectionService {

	public CandidateElectionServiceImpl() {
		System.out.println("In CandidateElectionServiceImpl CTOR "+getClass());
	}
	
	@Autowired
	private CandidateElectionRepository repoService;
	
	@Autowired
	private CandidateRepository candRepoService;
	
	@Autowired
	private ElectionRepository electRepoService;

	@Override
	public List<CandidateElection> getAll() {
		try {
			List<CandidateElection> celist = repoService.findAll();
			if(celist!=null) {
				return celist;
			}
			return null;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}


	@Override
	public String create(CandidateElectionDTO cedto) {
		try {
			Candidate c = candRepoService.findById(cedto.getCandidateId()).get();
			Election e = electRepoService.findById(cedto.getElectionId()).get();
			if(c!=null && e!=null) {
				if(!(repoService.existsByCandidateAndElection(c, e))) {
					CandidateElection ce = repoService.save(new CandidateElection(c, e));
					if(ce!=null) {
						return "Candidate for a election has been registered!";
					}
				}
				return "Candidate already registered. Candidate Registration Unsuccessful!";
			}			
			return null;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}
	
	
	
	
	

	
	
}
