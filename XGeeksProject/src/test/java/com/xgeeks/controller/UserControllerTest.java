package com.xgeeks.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgeeks.model.Availability;
import com.xgeeks.model.RequestInterview;
import com.xgeeks.model.Role;
import com.xgeeks.model.User;
import com.xgeeks.repository.UserRepository;
import com.xgeeks.service.UserService;
import com.xgeeks.util.UserHelper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private UserHelper userHelper;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void saveUserWithSuccess() throws Exception {
		String URL = "/addUser";
		
		
		User mockUser = new User();
		mockUser.setId(1);
		Mockito.when(userService.saveUser(Mockito.any(User.class))).thenReturn(mockUser);
		String expected = new ObjectMapper().writeValueAsString(mockUser);

		User user = new User();
		user.setName("Ines");
		user.setRole(Role.CANDIDATE);
		Set<Availability> availabilityList = new HashSet<Availability>();
		Availability availability = new Availability();
		availability.setStartTime(4);
		availability.setEndTime(7);
		availability.setDate(LocalDate.now());
		availabilityList.add(availability);
		user.setAvailability(availabilityList);
		
		String requestBody = new ObjectMapper().valueToTree(user).toString();

		mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(status().isOk())
				.andExpect(result -> assertEquals(expected, result.getResponse().getContentAsString()))
				.andReturn();
	}
	
	@Test
	public void findAvailabilityWithSuccess() throws Exception {
		String URL = "/findAvailability";
		
		Set<Availability> mockAvailabilitySet = new HashSet<Availability>();
		Availability mockAvailability = new Availability();
		mockAvailability.setDate(LocalDate.now());
		mockAvailability.setStartTime(5);
		mockAvailability.setEndTime(6);
		mockAvailabilitySet.add(mockAvailability);
		
		Mockito.when(userService.findAvailability(Mockito.any(RequestInterview.class))).thenReturn(mockAvailabilitySet);
		String expected = new ObjectMapper().writeValueAsString(mockAvailabilitySet);
		
		RequestInterview requestInterview = new RequestInterview();
		requestInterview.setId(3L);
		Set<Long> interviewerList = new HashSet<Long>();
		interviewerList.add(1L);
		interviewerList.add(2L);
		requestInterview.setInterviewerList(interviewerList);
		
		
		String requestBody = new ObjectMapper().valueToTree(requestInterview).toString();

		mockMvc.perform(MockMvcRequestBuilders
			      .get(URL).contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(status().isOk())
				.andExpect(result -> assertEquals(expected, result.getResponse().getContentAsString()))
				.andReturn();
	}
	
	


}
