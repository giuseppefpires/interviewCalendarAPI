package com.example.util;

import org.springframework.stereotype.Component;

import com.example.exception.InvalidAvailabilityException;
import com.example.exception.InvalidTimeException;
import com.example.exception.InvalidUserException;
import com.example.model.Availability;
import com.example.model.User;

@Component
public class UserHelper {

	public void validateAvailability(User user)
			throws InvalidTimeException, InvalidAvailabilityException {

		if (user.getAvailability() == null || user.getAvailability().isEmpty()) {
			throw new InvalidAvailabilityException();
		}

		for (Availability availability : user.getAvailability()) {

			if (availability == null || availability.getDate() == null) {
				throw new InvalidAvailabilityException();
			}

			if (availability.getStartTime() < 0 || availability.getStartTime() > 23 || availability.getEndTime() < 1
					|| availability.getEndTime() > 24 || availability.getEndTime() <= availability.getStartTime()
					|| availability.getStartTime() % 1 != 0 || availability.getEndTime() % 1 != 0) {
				throw new InvalidTimeException();
			}

		}
	}

	public void validateUser(User user) throws InvalidUserException {

		if (user == null || user.getName() == null || user.getName().trim().isEmpty() || user.getRole() == null) {
			throw new InvalidUserException();
		}
	}

}
