package com.app.practice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.practice.dtos.CandidateStatsDTO;
import com.app.practice.pojos.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

	@Query("""
			select new com.app.practice.dtos.CandidateStatsDTO(
                 c.name, 
				 round(count(vec1.voter.id) * 100.0 / 
                (select count(vec.voter.id) 
                 FROM Election e  
                 JOIN VoterElectionCandidate vec on vec.election.id = e.id  
                 WHERE e.id = :electionId), 2)) 
				 FROM Election e1 
				 JOIN VoterElectionCandidate vec1 on vec1.election.id = e1.id 
				 JOIN CandidateElection ce1 on ce1.id = vec1.candidateElection.id 
				 JOIN Candidate c on c.id = ce1.candidate.id 
				 WHERE e1.id = :electionId 
				 GROUP BY c.name
			""")
	List<CandidateStatsDTO> candidateResultByElection(@Param("electionId") Integer electionId);
}
