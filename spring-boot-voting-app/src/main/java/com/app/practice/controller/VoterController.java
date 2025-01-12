package com.app.practice.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.practice.dtos.VoterDTO;
import com.app.practice.dtos.VoterStatsDTO;
import com.app.practice.pojos.Voter;
import com.app.practice.services.VoterService;

@CrossOrigin
@RestController
@RequestMapping("/voters")
public class VoterController {

	@Autowired
	private VoterService service;
	
	public VoterController() {
		System.out.println("In votercontroller ctor +"+getClass());
	}
	
	@GetMapping("/all")
	public List<VoterDTO> getAll(){
		return service.getAllVoters();
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> createVoter(@RequestBody VoterDTO vdto) {
		
		if( vdto!=null) {
			String res = service.createVoter(vdto);
			if(res!=null) {
				return ResponseEntity.ok("Voter Created Successfully!");
			}
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/update/{voterId}")
	public ResponseEntity<String> updateVoter(@RequestBody Voter vt,@PathVariable Integer voterId){
		if(vt!=null && voterId!=null) {
			VoterDTO vdto = service.updateVoter(vt, voterId);
			if(vdto!=null) {
				return ResponseEntity.ok("Voter Updated Successfully!");
			}
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeVoter(@PathVariable Integer id){
		if(id!=null) {
			String response = service.remove(id);
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/voter-stats/{voterId}")
	public ResponseEntity<List<VoterStatsDTO>> getVoterStatsById(@PathVariable Integer voterId){
		if(voterId!=null) {
			List<VoterStatsDTO> vlist = service.getVoterStatsById(voterId);
			if(vlist!=null) {
				return ResponseEntity.ok(vlist);
			}
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.badRequest().body(Collections.emptyList());
	}
	
}
