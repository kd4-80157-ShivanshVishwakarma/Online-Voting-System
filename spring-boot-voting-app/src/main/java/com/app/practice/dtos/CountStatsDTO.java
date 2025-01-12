package com.app.practice.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class CountStatsDTO {
	private Long totalVoterCount;
	private Long electionVoterCount;
}
