package com.example.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.InvalidAvailabilityException;
import com.example.exception.InvalidRequestInterviewException;
import com.example.exception.InvalidTimeException;
import com.example.exception.InvalidUserException;
import com.example.exception.NoAvailabilityFoundException;
import com.example.exception.NoRegisteredUserException;
import com.example.exception.UserNotFoundException;
import com.example.model.Availability;
import com.example.model.RequestInterview;
import com.example.model.User;
import com.example.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/addUser")
	public User saveUser(@RequestBody User user) throws InvalidUserException, InvalidTimeException, InvalidAvailabilityException {
		User userResponse = userService.saveUser(user);
		return userResponse;
	}
	
	@GetMapping("/findUser")
	public User findUser(@RequestHeader Long id) throws UserNotFoundException {
		User user = userService.findUser(id);
		return user;
	}
	
	@DeleteMapping("/deleteUser")
	public User deleteUser(@RequestHeader Long id) throws UserNotFoundException {
		User user = userService.deleteUser(id);
		return user;
	}
	
	@PatchMapping("/updateUser")
	public User updateUser(@RequestBody User user) throws UserNotFoundException, InvalidUserException, InvalidTimeException, InvalidAvailabilityException{
		user = userService.updateUser(user);
		return user;
	}
	
	@GetMapping("/findAllCandidates")
	public List<User> findAllCandidates() throws NoRegisteredUserException {
		List<User> userList = userService.findAllCandidates();
		
		if(userList.isEmpty()) {
			throw new NoRegisteredUserException();
		}
		return userList;
	}
	
	@GetMapping("/findAllInterviewer")
	public List<User> findAllInterviewer() throws NoRegisteredUserException {
		List<User> userList = userService.findAllInterviewers();
		
		if(userList.isEmpty()) {
			throw new NoRegisteredUserException();
		}
		return userList;
	}
	
	@GetMapping("/findAllUsers")
	public List<User> findAllUsers() throws NoRegisteredUserException {
		List<User> userList = userService.findAllUsers();
		
		if(userList.isEmpty()) {
			throw new NoRegisteredUserException();
		}
		return userList;
	}
	
	@GetMapping("/findAvailability")
	public Set<Availability> findAvailability(@RequestBody RequestInterview schedule) throws InvalidRequestInterviewException, NoAvailabilityFoundException, UserNotFoundException {
 		Set<Availability> result = userService.findAvailability(schedule);
 		
 		if(result.isEmpty()) {
 			throw new NoAvailabilityFoundException();
 		}
 		
		return result;
	}
}


