package com.xgeeks.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.xgeeks.exception.InvalidAvailabilityException;
import com.xgeeks.exception.InvalidRequestInterviewException;
import com.xgeeks.exception.InvalidTimeException;
import com.xgeeks.exception.InvalidUserException;
import com.xgeeks.exception.UserNotFoundException;
import com.xgeeks.model.Availability;
import com.xgeeks.model.RequestInterview;
import com.xgeeks.model.User;

@Component
public interface UserService {

	
	User saveUser(User user) throws InvalidUserException, InvalidTimeException, InvalidAvailabilityException;
	
	User findUser(Long id) throws UserNotFoundException;
	
	User deleteUser(Long id) throws UserNotFoundException;
	
	List<User> findAllUsers();
	
	List<User> findAllCandidates();
	
	List<User> findAllInterviewers();
	
	User updateUser(User user) throws InvalidUserException, InvalidTimeException, InvalidAvailabilityException, UserNotFoundException;
	
	Set<Availability> findAvailability(RequestInterview requestInterview) throws InvalidRequestInterviewException, UserNotFoundException;
}
