package com.app.practice.services;

import java.util.List;

import com.app.practice.dtos.VoterDTO;
import com.app.practice.dtos.VoterStatsDTO;
import com.app.practice.pojos.*;

public interface VoterService {

	List<VoterDTO> getAllVoters();
	String createVoter(VoterDTO vdto);
	VoterDTO updateVoter(Voter vtr,Integer id); 
	String remove(Integer id);
	List<VoterStatsDTO> getVoterStatsById(Integer id);
}
