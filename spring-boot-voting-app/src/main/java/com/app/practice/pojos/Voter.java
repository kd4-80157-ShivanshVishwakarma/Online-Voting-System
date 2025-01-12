package com.app.practice.pojos;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="voter")
@Getter
@Setter
@NoArgsConstructor
public class Voter extends Base{
	
	@Column(length = 255,nullable = false)
	private String name;
	
	@Column(nullable = false)
	private LocalDate dob;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;
	
	@Column(columnDefinition = "TEXT",nullable = false)
	private String address;
	
	@Column(unique = true)
	private Long aadhar;
	
	@OneToMany(mappedBy = "voter")
	@JsonIgnore()
	private List<VoterElectionCandidate> votes;
	
}
