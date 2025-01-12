package com.app.practice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.practice.dtos.VoteDTO;
import com.app.practice.pojos.Candidate;
import com.app.practice.pojos.CandidateElection;
import com.app.practice.pojos.Election;
import com.app.practice.pojos.Voter;
import com.app.practice.pojos.VoterElectionCandidate;

public interface VoterElectionCandidateRepository extends JpaRepository<VoterElectionCandidate, Integer> {

	@Query("""
			select ce from Election e 
			join CandidateElection ce on ce.election.id = e.id
			where e.id = :electionId
			""")
	List<CandidateElection> findCandidateElectionByElectionId(@Param("electionId") Integer electionId);
	
	@Query("""
			select 
				 new com.app.practice.dtos.VoteDTO(
				 	vec.id, 
					vec.election.id, 
					vec.candidateElection.candidate.id, 
					vec.voter.id, 
					vec.voter.name, 
					vec.candidateElection.candidate.name, 
					vec.election.electionType 
				 )  
			from VoterElectionCandidate vec
			""")
	List<VoteDTO> findAllVotes();

//	boolean existsByElectionAndVoterAndCandidateElection(Election election, Voter voter, CandidateElection candidateElection);
	
	boolean existsByElectionAndVoter(Election election, Voter voter);
}
