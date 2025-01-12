package com.app.practice.dtos;

import java.time.LocalDate;

import com.app.practice.pojos.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
public class VoterDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	 private Integer id;
	 private String name;
	 
	 private LocalDate dob;
		
	 
	 private Gender gender;
		
	 
     private String address;
     
     private Long aadhar;
	 
}
