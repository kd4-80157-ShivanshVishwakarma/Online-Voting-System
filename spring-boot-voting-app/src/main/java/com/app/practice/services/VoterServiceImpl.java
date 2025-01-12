package com.app.practice.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.practice.dtos.VoterDTO;
import com.app.practice.dtos.VoterStatsDTO;
import com.app.practice.pojos.Voter;
import com.app.practice.repositories.VoterRepository;

@Service
@Transactional
public class VoterServiceImpl implements VoterService {

	@Autowired
	private VoterRepository repositoryService;
	
	@Autowired
	private ModelMapper mapper;
	
	public VoterServiceImpl() {
		System.out.println("In voterserviceimpl ctor "+getClass());
	}
	
	@Override
	public List<VoterDTO> getAllVoters() {
		try {
			List<VoterDTO> votrs = repositoryService.findAll().stream().map(vtr-> mapper.map(vtr, VoterDTO.class)).toList();
			if(votrs !=null) {
				return votrs;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String createVoter(VoterDTO vdto) {
		Voter v = repositoryService.save(mapper.map(vdto, Voter.class));
		if(v!=null) {
			return "Registration Successful";
		}
		return "Internal Server Error!";
		
	}

	@Override
	public VoterDTO updateVoter(Voter vtr,Integer id) {
	     try {
	    	vtr.setId(id);
	    	System.out.println(id);
			Voter votr = repositoryService.save(vtr);
			if(votr!=null) {
				VoterDTO vdto = mapper.map(votr, VoterDTO.class);
				return vdto;
			}
			return null;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public String remove(Integer id) {
		try {
			if(repositoryService.existsById(id)) {
				repositoryService.deleteById(id);
				if (!repositoryService.existsById(id)) {
	                return "Voter deleted successfully!";
	            } else {
	                return "Failed to delete the voter with ID.";
	            }
			}
			else {
				return "Voter does not exist in DB";
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}

	@Override
	public List<VoterStatsDTO> getVoterStatsById(Integer id) {
		try {
			List<VoterStatsDTO> list = repositoryService.findVoterStatsById(id);
			if(list !=null) {
				return list;
			}
			return null;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	
}
