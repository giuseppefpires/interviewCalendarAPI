package com.example.model;

import java.util.Set;

public class RequestInterview {

	private Long id;
	
	private Set<Long> interviewerList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Long> getInterviewerList() {
		return interviewerList;
	}

	public void setInterviewerList(Set<Long> interviewerList) {
		this.interviewerList = interviewerList;
	}
	
	
}
