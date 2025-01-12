package com.app.practice.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.practice.dtos.VoteDTO;
import com.app.practice.dtos.VoterElectionCandidateDTO;
import com.app.practice.dtos.VoterElectionCandidateRDTO;
import com.app.practice.pojos.Candidate;
import com.app.practice.pojos.CandidateElection;
import com.app.practice.pojos.Election;
import com.app.practice.pojos.Voter;
import com.app.practice.pojos.VoterElectionCandidate;
import com.app.practice.repositories.CandidateElectionRepository;
import com.app.practice.repositories.CandidateRepository;
import com.app.practice.repositories.ElectionRepository;
import com.app.practice.repositories.VoterElectionCandidateRepository;
import com.app.practice.repositories.VoterRepository;

@Service
@Transactional
public class VoterElectionCandidateServiceImpl implements VoterElectionCandidateService{

	public VoterElectionCandidateServiceImpl() {
		System.out.println("In ctor VoterElectionCandidateServiceImpl "+getClass());
	}
	
	@Autowired
	private VoterElectionCandidateRepository repoService;
	
	@Autowired
	private ElectionRepository electRepoService;
	 
	@Autowired
	private VoterRepository voterRepoService;
	
	@Autowired
	private CandidateElectionRepository ceRepoService;
	

	@Override
	public List<VoterElectionCandidateDTO> getAll() {
		try {
			List<VoterElectionCandidate> list = repoService.findAll();
			if(list!=null) {
				return list.stream().map(vec -> new VoterElectionCandidateDTO(vec.getElection().getId(),vec.getVoter().getId(), vec.getCandidateElection())).toList();
			}return null;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}


//	@Override
//	public String saveVEC(VoterElectionCandidateRDTO vecdto) {
//		try {
//			Election e = electRepoService.findById(vecdto.getElectionId()).get();
//			Voter v = voterRepoService.findById(vecdto.getVoterId()).get();
//			CandidateElection ce = ceRepoService.findById(vecdto.getCandidateElectionId()).get();
//			
//			if(e!=null && v!=null && ce!=null) {
//				if(!(repoService.existsByElectionAndVoterAndCandidateElection(e, v, ce))) {
//					VoterElectionCandidate vec = repoService.save(new VoterElectionCandidate(e, v, ce));
//					return vec!=null? "Voting successful!": null;
//				}
//				return "You have already voted for this candidate in this election!";
//			}
//			return null;
//		} catch (RuntimeException e) {
//			return e.getMessage();
//		}
//	}
	
	@Override
	public String saveVEC(VoterElectionCandidateRDTO vecdto) {
		try {
			Election e = electRepoService.findById(vecdto.getElectionId()).get();
			Voter v = voterRepoService.findById(vecdto.getVoterId()).get();
			CandidateElection ce = ceRepoService.findById(vecdto.getCandidateElectionId()).get();
			
			if(e!=null && v!=null && ce!=null) {
				if(!(repoService.existsByElectionAndVoter(e, v))) {
					VoterElectionCandidate vec = repoService.save(new VoterElectionCandidate(e, v, ce));
					return vec!=null? "Voting successful!": null;
				}
				return "You have already voted for this election!";
			}
			return null;
		} catch (RuntimeException e) {
			return e.getMessage();
		}
	}


	@Override
	public List<CandidateElection> getCandidateElectionsByEid(Integer id) {
		 try {
			if(electRepoService.existsById(id)) {
				List<CandidateElection> candidateElections = repoService.findCandidateElectionByElectionId(id);
				if(candidateElections!=null) {
					return candidateElections;
				}
				return null;
			}
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}


	@Override
	public List<VoteDTO> getAllVotes() {
		try {
			List<VoteDTO> listOfVotes = repoService.findAllVotes();
			if(listOfVotes!=null) {
				return listOfVotes;
			}return null;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	
	
}
