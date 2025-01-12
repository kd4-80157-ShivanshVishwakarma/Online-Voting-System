package com.app.practice.services;

import java.util.List;

import com.app.practice.dtos.CountStatsDTO;
import com.app.practice.dtos.ElectionDTO;
import com.app.practice.dtos.GenericResponseDTO;
import com.app.practice.dtos.VoterDTO;
import com.app.practice.pojos.Election;
import com.app.practice.pojos.Gender;

public interface ElectionService {

	CountStatsDTO getTotalCountVoters(Integer ElectionId);
	List<VoterDTO> findVotersByElectionAndGender(Integer electionId, Gender gender);
	List<GenericResponseDTO> getStatsByElectionId(Integer electionId);
	Election saveElection(Election elect);
	List<Election> getAll();
	String update(ElectionDTO edto, Integer electionId);
	String remove(Integer id);
}
