package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.exception.InvalidAvailabilityException;
import com.example.exception.InvalidRequestInterviewException;
import com.example.exception.InvalidTimeException;
import com.example.exception.InvalidUserException;
import com.example.exception.UserNotFoundException;
import com.example.model.Availability;
import com.example.model.RequestInterview;
import com.example.model.Role;
import com.example.model.User;

public class UserServiceTest {

	@Mock
	private UserServiceImpl userService;

	@BeforeEach
	public void setUpTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveUserWithSuccess() throws InvalidUserException, InvalidTimeException, InvalidAvailabilityException {

		User mockUser = createUserTest("Ines", 1L, Role.CANDIDATE, 4, 7);
		Mockito.when(userService.saveUser(Mockito.any(User.class))).thenReturn(mockUser);

		User user = createUserTest("Ines", 1L, Role.CANDIDATE, 4, 7);
		User response = userService.saveUser(user);

		assertEquals(mockUser, response);
	}

	@Test
	public void findAvailabilityWithSuccess() throws InvalidRequestInterviewException, UserNotFoundException {

		User mockUserInterview_1 = createUserTest("Ines", 1L, Role.INTERVIEWER, 4, 7);
		Mockito.when(userService.findUser(1L)).thenReturn(mockUserInterview_1);
		

		User mockUserInterview_2 = createUserTest("Ingrid", 2L, Role.INTERVIEWER, 5, 8);
		Mockito.when(userService.findUser(2L)).thenReturn(mockUserInterview_2);

		User mockUserCandidate = createUserTest("Carl", 3L, Role.CANDIDATE, 6, 7);
		Mockito.when(userService.findUser(3L)).thenReturn(mockUserCandidate);

		Mockito.when(userService.findAvailability(Mockito.any(RequestInterview.class))).thenCallRealMethod();
		Mockito.doCallRealMethod().when(userService).loadSchedule(Mockito.anyMap(), Mockito.anySet(),
				Mockito.anyBoolean());

		RequestInterview requestInterview = createRequestInterview(3L, 1L, 2L);

		Set<Availability> expected = new HashSet<Availability>();
		Availability availability = new Availability();
		availability.setDate(LocalDate.now());
		availability.setStartTime(6);
		availability.setEndTime(7);
		expected.add(availability);

		Set<Availability> actual = this.userService.findAvailability(requestInterview);

		assertEquals(expected, actual);

	}
	
	@Test
	public void findAvailabilityWithUnregisteredUsers() throws InvalidRequestInterviewException, UserNotFoundException {
		Mockito.when(userService.findAvailability(Mockito.any(RequestInterview.class))).thenCallRealMethod();
		
		Mockito.when(userService.findUser(Mockito.anyLong())).thenThrow(UserNotFoundException.class);
		RequestInterview requestInterview = createRequestInterview(3L, 1L, 2L);
		
		assertThrows(UserNotFoundException.class, () -> {
			userService.findAvailability(requestInterview);
		});
	}
	
	@Test
	public void findAvailabilityWithNullInterviewUsers() throws InvalidRequestInterviewException, UserNotFoundException {
		Mockito.when(userService.findAvailability(Mockito.any(RequestInterview.class))).thenCallRealMethod();
		
		Mockito.when(userService.findUser(Mockito.anyLong())).thenThrow(UserNotFoundException.class);
		RequestInterview requestInterview = createRequestInterview(3L, null, null);
		
		assertThrows(InvalidRequestInterviewException.class, () -> {
			userService.findAvailability(requestInterview);
		});
	}
	
	@Test
	public void findAvailabilityWithEmptyInterviewUsers() throws InvalidRequestInterviewException, UserNotFoundException {
		Mockito.when(userService.findAvailability(Mockito.any(RequestInterview.class))).thenCallRealMethod();
		
		Mockito.when(userService.findUser(Mockito.anyLong())).thenThrow(UserNotFoundException.class);
		RequestInterview requestInterview = new RequestInterview();
		requestInterview.setId(1L);
		requestInterview.setInterviewerList(new HashSet<Long>());
		
		
		assertThrows(InvalidRequestInterviewException.class, () -> {
			userService.findAvailability(requestInterview);
		});
	}
	
	
	
	
	private User createUserTest(String name, Long id, Role role, float startTime, float endTime) {
		
		User user = new User();
		user.setName(name);
		user.setId(id);
		user.setRole(role);
		
		Set<Availability> availabilitySet = new HashSet<Availability>();
		Availability availability = new Availability.Builder().startTime(startTime).endTime(endTime)
				.date(LocalDate.now()).build();
		availabilitySet.add(availability);
		user.setAvailability(availabilitySet);
		
		return user;
	}
	
	private RequestInterview createRequestInterview(Long id_candidate, Long id_interview_1, Long id_interview_2) {
		RequestInterview requestInterview = new RequestInterview();
		requestInterview.setId(id_candidate);
		Set<Long> interviewerList = new HashSet<Long>();
		interviewerList.add(id_interview_1);
		interviewerList.add(id_interview_2);
		requestInterview.setInterviewerList(interviewerList);
		
		return requestInterview;
	}

}
