package com.app.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.practice.pojos.Candidate;
import com.app.practice.pojos.CandidateElection;
import com.app.practice.pojos.Election;

public interface CandidateElectionRepository extends JpaRepository<CandidateElection, Integer> {
	boolean existsByCandidateAndElection(Candidate candidate, Election election);
}
