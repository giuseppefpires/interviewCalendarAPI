package com.example.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
	CANDIDATE("Candidate"), INTERVIEWER("Interviewer");
	
	private String value;
	
	
	private Role(String value) {
		this.value = value;
	}
	
	public static Role getEnum(String role) {
		if(role.equals(Role.CANDIDATE.getValue())){
			return Role.CANDIDATE;
		}else {
			
			return Role.INTERVIEWER; 
		}
	}
	@JsonValue
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
