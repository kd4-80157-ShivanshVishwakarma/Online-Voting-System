package com.app.practice.repositories;

import com.app.practice.dtos.CountStatsDTO;
import com.app.practice.dtos.GenericResponseDTO;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.app.practice.pojos.Election;
import com.app.practice.pojos.Gender;
import com.app.practice.pojos.Voter;

public interface ElectionRepository extends JpaRepository<Election,Integer> {

	@Query(""" 
			select new com.app.practice.dtos.CountStatsDTO((select count(v.id) from Voter v),count(vec.voter.id)) from Election e 
			inner join VoterElectionCandidate vec on vec.election.id=e.id 
			where e.id = :electionId
			""")
	CountStatsDTO findCountStatsById(@Param("electionId") Integer electionId);
	
	@Query("select v from Election e "+
			"inner join VoterElectionCandidate vec on vec.election.id = e.id "+
			"inner join Voter v on v.id = vec.voter.id "+
			"where e.id = :electionId and v.gender = :gender")
	List<Voter> findByElectionAndGender(@Param("electionId") Integer electionId, @Param("gender") Gender gender);
	
	@Query("""
			select new com.app.practice.dtos.GenericResponseDTO(c.name, 
		        round(cast(count(vec1.voter.id) as double) * 100 / 
              	(select cast(count(vec.id) as double) 
                from VoterElectionCandidate vec 
                where vec.election.id = :electionId), 2))		    
		    from Election e1 
		    join VoterElectionCandidate vec1 on vec1.election.id = e1.id
		    join CandidateElection ce on ce.id = vec1.candidateElection.id
		    join Candidate c on c.id = ce.candidate.id
		    where  e1.id = :electionId
		    group by c.name
			""")
	List<GenericResponseDTO> getStatsByElectionId(@Param("electionId") Integer electionId);

}

