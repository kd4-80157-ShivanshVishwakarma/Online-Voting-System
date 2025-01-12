package com.app.practice.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.practice.dtos.CandidateDTO;
import com.app.practice.dtos.CandidateStatsDTO;
import com.app.practice.pojos.Candidate;
import com.app.practice.services.CandidateService;

import lombok.Getter;

@CrossOrigin
@RestController
@RequestMapping("/candidates")
public class CandidateController {
	
	public CandidateController() {
		System.out.println("In Candidate Controller Ctor"+getClass());
	}

	@Autowired
	private CandidateService service;
	
	@PostMapping("/create")
	public ResponseEntity<String> createCandidate(@RequestBody CandidateDTO cdto){
		if(cdto!=null) {
			Candidate response = service.create(cdto);
			if(response!=null) {
				return ResponseEntity.ok("Candidate has been created!");
			}
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.badRequest().body("Request body or parameter not mapped!");
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Candidate>> getCandidates(){
		List<Candidate> list = service.getAll();
		if(list!=null) {
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.internalServerError().build();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateCandidate(@RequestBody CandidateDTO candto,@PathVariable Integer id){
		if(candto!=null && id!=null) {
			String resp = service.update(candto, id);
			if(resp!=null) {
				return ResponseEntity.ok(resp);
			}
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.badRequest().body("Request body or parameter not mapped!");
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeCandidate(@PathVariable Integer id){
		if(id!=null) {
			String response = service.remove(id);
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body("Request body or parameter not mapped!");
	}
	
	@GetMapping("/candidateStats/{electionId}")
	public ResponseEntity<List<CandidateStatsDTO>> getCandidateStats(@PathVariable Integer electionId){
		if(electionId!=null) {
			List<CandidateStatsDTO> respList = service.candidateStatsByElectionId(electionId);
			if(respList!=null) {
				return ResponseEntity.ok(respList);
			}
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.badRequest().body(Collections.emptyList());
	}
	
}
