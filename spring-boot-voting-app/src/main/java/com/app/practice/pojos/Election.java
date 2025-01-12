package com.app.practice.pojos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="election")
@Getter
@Setter
@NoArgsConstructor
public class Election extends Base {

	@Enumerated(EnumType.STRING)
	@Column(name="election_type")
	private ElectionType electionType;
	
	@OneToMany(mappedBy = "election")
	@JsonIgnore
	private List<CandidateElection> candidateElections;
	
	@Column(name = "election_date")
	private LocalDate electionDate;
	
	@OneToMany(mappedBy = "election")
	@JsonIgnore
    private List<VoterElectionCandidate> votes;
	
}
