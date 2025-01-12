package com.app.practice.dtos;

import lombok.*;

@Getter
@Setter
public class GenericResponseDTO {
	
	private String key;
	private Double value;
	
	public GenericResponseDTO(String key, Double value) {
		
		this.key = key;
		this.value = value;
	}	
}
