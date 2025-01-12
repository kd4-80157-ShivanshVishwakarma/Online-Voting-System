package com.app.practice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.practice.dtos.VoterStatsDTO;
import com.app.practice.pojos.Voter;

public interface VoterRepository extends JpaRepository<Voter, Integer> {
	
	@Query("""
			select new com.app.practice.dtos.VoterStatsDTO(c.name,e.electionType) from Voter v 
			join VoterElectionCandidate vec on vec.voter.id = v.id 
			join Election e on vec.election.id = e.id 
			join CandidateElection ce on vec.candidateElection.id = ce.id 
			join Candidate c on ce.candidate.id = c.id 
			where v.id=  :voterId
			""")
	List<VoterStatsDTO> findVoterStatsById(@Param("voterId") Integer voterId);
}
