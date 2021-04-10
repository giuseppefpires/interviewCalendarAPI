package com.example.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.exception.InvalidAvailabilityException;
import com.example.exception.InvalidUserException;
import com.example.model.Availability;
import com.example.model.Role;
import com.example.model.User;

public class UserHelperTest {

	private static UserHelper userHelper;
	
	@BeforeAll
	public static void setUpTest() {
		userHelper = new UserHelper();
	}
	
	/**
	 * Validate User tests
	 *
	 */

	@Test
	public void validateUserWithSuccess() {
		User user = new User();
		user.setName("Ines");
		user.setRole(Role.CANDIDATE);
		Set<Availability> availabilityList = new HashSet<Availability>();
		availabilityList.add(new Availability());
		user.setAvailability(availabilityList);

		assertDoesNotThrow(() -> userHelper.validateUser(user));
	}

	@Test
	public void validateUserWithEmptyUser() {
		User user = new User();

		assertThrows(InvalidUserException.class, () -> {
			userHelper.validateUser(user);
		});

	}

	@Test
	public void validateUserWithOutRole() {
		User user = new User();
		Set<Availability> availabilityList = new HashSet<Availability>();
		availabilityList.add(new Availability());
		user.setAvailability(availabilityList);

		assertThrows(InvalidUserException.class, () -> {
			userHelper.validateUser(user);
		});

	}

	@Test
	public void validateAvailabilityHoursWithEmptyAvailability() {
		User user = new User();
		user.setRole(Role.CANDIDATE);
		Set<Availability> availabilityList = new HashSet<Availability>();
		availabilityList.add(new Availability());
		user.setAvailability(availabilityList);

		assertThrows(InvalidAvailabilityException.class, () -> {
			userHelper.validateAvailability(user);
		});

	}

	@Test
	public void validateAvailabilityHoursWithNullAvailability() {
		User user = new User();
		user.setRole(Role.CANDIDATE);
		Set<Availability> availabilityList = new HashSet<Availability>();
		availabilityList.add(null);
		user.setAvailability(availabilityList);

		assertThrows(InvalidAvailabilityException.class, () -> {
			userHelper.validateAvailability(user);
		});

	}

	@Test
	public void validateAvailabilityHoursWithSuccess() {
		User user = new User();
		Set<Availability> availabilityList = new HashSet<Availability>();
		Availability availability = new Availability();
		availability.setStartTime(4);
		availability.setEndTime(7);
		availability.setDate(LocalDate.now());
		availabilityList.add(availability);
		user.setAvailability(availabilityList);

		assertDoesNotThrow(() -> userHelper.validateAvailability(user));

	}

	@Test
	public void validateAvailabilityHoursWithSuccessEmptyAvailability() {
		User user = new User();
		Set<Availability> availabilityList = new HashSet<Availability>();
		Availability availability = new Availability();
		availabilityList.add(availability);
		user.setAvailability(availabilityList);

		assertThrows(InvalidAvailabilityException.class, () -> {
			userHelper.validateAvailability(user);
		});

	}

	@Test
	public void validateUserWithNullAvailabilityList() {
		User user = new User();
		user.setRole(Role.CANDIDATE);
		user.setAvailability(null);

		assertThrows(InvalidUserException.class, () -> {
			userHelper.validateUser(user);
		});

	}

	@Test
	public void validateAvailabilityHoursWithEmptyDate() {
		User user = new User();
		Set<Availability> availabilityList = new HashSet<Availability>();
		Availability availability = new Availability();
		availability.setStartTime(4);
		availability.setEndTime(7);
		availabilityList.add(availability);
		user.setAvailability(availabilityList);
		assertThrows(InvalidAvailabilityException.class, () -> {
			userHelper.validateAvailability(user);
		});

	}

	@Test
	public void validateAvailabilityHoursWithNullDate() {
		User user = new User();
		Set<Availability> availabilityList = new HashSet<Availability>();
		Availability availability = new Availability();
		availability.setStartTime(4);
		availability.setEndTime(7);
		availability.setDate(null);
		availabilityList.add(availability);
		user.setAvailability(availabilityList);

		assertThrows(InvalidAvailabilityException.class, () -> {
			userHelper.validateAvailability(user);
		});

	}

}
