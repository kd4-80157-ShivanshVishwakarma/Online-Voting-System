package com.app.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.practice.dtos.CandidateElectionDTO;
import com.app.practice.pojos.CandidateElection;
import com.app.practice.services.CandidateElectionService;

@CrossOrigin
@RestController
@RequestMapping("/candidatelections")
public class CandidateElectionController {

	public CandidateElectionController() {
		System.out.println("In ctor of CandidateElectionController "+getClass());
	}
	
	@Autowired
	private CandidateElectionService service;
	
	@GetMapping("/all")
	public ResponseEntity<List<CandidateElection>> getAll(){
		List<CandidateElection> list = service.getAll();
		if(list!=null) {
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.internalServerError().build();
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createCandidateElection(@RequestBody CandidateElectionDTO ce){
		if(ce!=null) {
			String response = service.create(ce);
			if(response!=null) {
				return ResponseEntity.ok(response);
			}
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.noContent().build();
	}

	
	
}
