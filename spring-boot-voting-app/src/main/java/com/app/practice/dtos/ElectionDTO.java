package com.app.practice.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.app.practice.pojos.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
public class ElectionDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer id;
		
	private ElectionType electionType;
		
	private LocalDate electionDate;
	
}
