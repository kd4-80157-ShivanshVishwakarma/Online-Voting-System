package com.app.practice.dtos;

import com.app.practice.pojos.ElectionType;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class VoterStatsDTO {
	
	private String candidateName;
	private ElectionType electionType;
}
