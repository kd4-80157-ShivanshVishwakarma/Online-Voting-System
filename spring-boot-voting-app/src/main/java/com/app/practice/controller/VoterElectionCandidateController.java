package com.app.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.practice.dtos.VoteDTO;
import com.app.practice.dtos.VoterElectionCandidateDTO;
import com.app.practice.dtos.VoterElectionCandidateRDTO;
import com.app.practice.pojos.Candidate;
import com.app.practice.pojos.CandidateElection;
import com.app.practice.pojos.VoterElectionCandidate;
import com.app.practice.services.VoterElectionCandidateService;

import lombok.Getter;

@CrossOrigin("*")
@RestController
@RequestMapping("/voter-election-candidates")
public class VoterElectionCandidateController {

	public VoterElectionCandidateController() {
		System.out.println("In ctor of VoterElectionCandidateController"+ getClass());
	}
	
	@Autowired
	private VoterElectionCandidateService service;
	
	@GetMapping("/get/all")
	public ResponseEntity<List<VoterElectionCandidateDTO>> getByElectionId(){
		List<VoterElectionCandidateDTO> list = service.getAll();
		if(list!=null) {
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.internalServerError().build();
	}

	@PostMapping("/cast")
	public ResponseEntity<?> castVote(@RequestBody VoterElectionCandidateRDTO vecdto){
		if(vecdto!=null) {
			String resp = service.saveVEC(vecdto);
			return ResponseEntity.ok(resp);
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/candidates/{electionId}")
	public ResponseEntity<List<CandidateElection>> getByElectionId(@PathVariable Integer electionId){
		List<CandidateElection> listOfCandidateElections = service.getCandidateElectionsByEid(electionId);
		if(listOfCandidateElections!=null) {
			return ResponseEntity.ok(listOfCandidateElections);
		}
		return ResponseEntity.internalServerError().build();
	}
	
	@GetMapping("/get/votes")
	public ResponseEntity<List<VoteDTO>> getAllVotes(){
		List<VoteDTO> list = service.getAllVotes();
		if(list!=null) {
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.internalServerError().build();
	}
	
}
