package com.app.practice.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.practice.dtos.CountStatsDTO;
import com.app.practice.dtos.ElectionDTO;
import com.app.practice.dtos.GenericResponseDTO;
import com.app.practice.dtos.VoterDTO;
import com.app.practice.pojos.Candidate;
import com.app.practice.pojos.Election;
import com.app.practice.pojos.Gender;
import com.app.practice.pojos.Voter;
import com.app.practice.repositories.ElectionRepository;

@Transactional
@Service
public class ElectionServiceImpl implements ElectionService {

	public ElectionServiceImpl() {
		System.out.println("In ctor of "+getClass());
	}
	
	@Autowired
	private ElectionRepository repositoryService;
	
	@Autowired 
	private ModelMapper mapper;
	
	@Override
	public CountStatsDTO getTotalCountVoters(Integer ElectionId) {
		try {
			return repositoryService.findCountStatsById(ElectionId);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<VoterDTO> findVotersByElectionAndGender(Integer electionId, Gender gender) {
		
		List<Voter> vtrs = repositoryService.findByElectionAndGender(electionId, gender);
		List<VoterDTO> vtrsdto = vtrs.stream().map(vtr -> mapper.map(vtr, VoterDTO.class)).toList();
		return vtrsdto;
	}
	
	@Override
	public List<GenericResponseDTO> getStatsByElectionId(Integer electionId) {
		try {
			List<GenericResponseDTO> grlist = repositoryService.getStatsByElectionId(electionId);
			 if(grlist!=null) {
				 return grlist;
			 }
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Election saveElection(Election elect) {
		
		try {
			Election elec = repositoryService.save(elect);
			if(elec!=null) {
				return elec;
			}
			return null;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Election> getAll() {
		try {
			List<Election> grlist = repositoryService.findAll();
			 if(grlist!=null) {
				 return grlist;
			 }
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public String update(ElectionDTO edto, Integer electionId) {
		try {	
			Election elect = mapper.map(edto, Election.class);
			elect.setId(electionId);
			Election e = repositoryService.save(elect);
			if(e!=null) {
				return "Election updated successfully!";
			}
			return null;
		} catch (RuntimeException e) {
			return e.getMessage();
		}
	}

	@Override
	public String remove(Integer id) {
		try {
			if(repositoryService.existsById(id)) {
				repositoryService.deleteById(id);
				if (!repositoryService.existsById(id)) {
	                return "Election deleted successfully!";
	            } else {
	                return "Failed to delete the election with ID.";
	            }
			}
			else {
				return "Election doesn't exist in backend system!";
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}
	
	

	
}
