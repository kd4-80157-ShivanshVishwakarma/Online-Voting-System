package com.app.practice.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.practice.dtos.CountStatsDTO;
import com.app.practice.dtos.ElectionDTO;
import com.app.practice.dtos.GenericResponseDTO;
import com.app.practice.dtos.VoterDTO;
import com.app.practice.pojos.Election;
import com.app.practice.pojos.Gender;
import com.app.practice.services.ElectionService;

@CrossOrigin
@RestController
@RequestMapping("/elections")
public class ElectionController {

	public ElectionController() {
		System.out.println("In ctor of "+getClass());
	}
	
	@Autowired
	private ElectionService service;
	
	@GetMapping("/{electionId}/count-stats")
	public ResponseEntity<?> getVotersCountByElectionId(@PathVariable Integer electionId){
		
		if(electionId!=null) {
			CountStatsDTO votersCount = service.getTotalCountVoters(electionId);
			if(votersCount!=null) {
				return ResponseEntity.ok(votersCount);
			}
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/voters")
	public ResponseEntity<List<VoterDTO>> getVotersByElectionAndGender(@RequestParam Integer electionId,@RequestParam Gender gender){
		List<VoterDTO> voters = service.findVotersByElectionAndGender(electionId, gender);
		if(voters!=null) {
			return ResponseEntity.ok(voters);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@GetMapping("/{electionId}/candidate-stats")
	public ResponseEntity<List<GenericResponseDTO>> getStatsByElectionId(@PathVariable Integer electionId){
		List<GenericResponseDTO> list=  service.getStatsByElectionId(electionId);
		if(list!=null) {
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.internalServerError().build();
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createElection(@RequestBody Election elec){
		if(elec!=null) {
			Election e = service.saveElection(elec);
			if(e!=null) {
				return ResponseEntity.ok("Election Registered Successfully!");
			}
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Election>> getElections(){
		List<Election> list=  service.getAll();
		if(list!=null) {
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.internalServerError().build();
	}
	
	@PutMapping("/update/{electionId}")
	public ResponseEntity<String> updateElection(@RequestBody ElectionDTO edto,@PathVariable Integer electionId){
		if(edto!=null && electionId!=null) {
			String resp = service.update(edto, electionId);
			if(resp!=null) {
				return ResponseEntity.ok(resp);
			}
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeElection(@PathVariable Integer id){
		if(id!=null) {
			String response = service.remove(id);
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.noContent().build();
	}
	
	
}
