package com.app.practice.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.practice.dtos.CandidateDTO;
import com.app.practice.dtos.CandidateStatsDTO;
import com.app.practice.pojos.Candidate;
import com.app.practice.repositories.CandidateRepository;

@Service
@Transactional
public class CandidateServiceImpl implements CandidateService {

	public CandidateServiceImpl() {
		System.out.println("In ctor of Candidate serviceimpl "+getClass());
	}
	
	@Autowired
	private CandidateRepository repoService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Candidate create(CandidateDTO cdto) {
		try {
			Candidate cand = mapper.map(cdto, Candidate.class);
			Candidate c = repoService.save(cand);
			if(c!=null) {
				return c;
			}
			return null;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Candidate> getAll() {
		try {
			return repoService.findAll();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public String update(CandidateDTO cdto, Integer id) {
		try {
			
			Candidate can = mapper.map(cdto, Candidate.class);
			can.setId(id);
			Candidate c = repoService.save(can);
			if(c!=null) {
				return "Candidate updated successfully!";
			}
			return null;
		} catch (RuntimeException e) {
			return e.getMessage();
		}
	}

	@Override
	public String remove(Integer id) {
		try {
			if(repoService.existsById(id)) {
				repoService.deleteById(id);
				if (!repoService.existsById(id)) {
	                return "Candidate deleted successfully!";
	            } else {
	                return "Failed to delete the Candidate with ID.";
	            }
			}
			else {
				return "Candidate doesn't exist in backend system!";
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}

	@Override
	public List<CandidateStatsDTO> candidateStatsByElectionId(Integer electionId) {
		try {
			List<CandidateStatsDTO> list = repoService.candidateResultByElection(electionId);
			if(list!=null) {
				return list;
			}
			return null;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
