package com.app.practice.dtos;

import com.app.practice.pojos.ElectionType;

import lombok.*;

@Data
@AllArgsConstructor
public class VoteDTO {

	private Integer id;
	private Integer electionId;
	private Integer candidateId;
	private Integer voterId;
	private String voterName;
	private String candidateName;
	private ElectionType electionType;
}
