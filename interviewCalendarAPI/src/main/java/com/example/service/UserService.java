package com.example.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.example.exception.InvalidAvailabilityException;
import com.example.exception.InvalidRequestInterviewException;
import com.example.exception.InvalidTimeException;
import com.example.exception.InvalidUserException;
import com.example.exception.UserNotFoundException;
import com.example.model.Availability;
import com.example.model.RequestInterview;
import com.example.model.User;

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
