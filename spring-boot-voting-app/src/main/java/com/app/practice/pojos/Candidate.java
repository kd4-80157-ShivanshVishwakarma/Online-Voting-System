package com.app.practice.pojos;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="candidate")
@Getter
@Setter
@NoArgsConstructor
public class Candidate extends Base {

	@Column(length = 255,nullable = false)
	private String name;
	
	@Column(nullable = false)
	private LocalDate dob;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;
	
	@Column(columnDefinition = "TEXT",nullable = false)
	private String address;
	
	@Column(length = 255,nullable = false)
	private String party;
	
	@Column(unique = true)
	private Long aadhar;
	
	@OneToMany(mappedBy = "candidate")
	@JsonIgnore
	private List<CandidateElection> candidateElections;
		
}
