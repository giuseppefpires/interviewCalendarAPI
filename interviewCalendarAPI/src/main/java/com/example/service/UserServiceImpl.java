package com.example.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.InvalidAvailabilityException;
import com.example.exception.InvalidRequestInterviewException;
import com.example.exception.InvalidTimeException;
import com.example.exception.InvalidUserException;
import com.example.exception.UserNotFoundException;
import com.example.model.Availability;
import com.example.model.RequestInterview;
import com.example.model.Role;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.util.UserHelper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserHelper userHelper;

	public User saveUser(User user) throws InvalidUserException, InvalidTimeException, InvalidAvailabilityException {
		userHelper.validateUser(user);
		userHelper.validateAvailability(user);
		return userRepository.save(user);
	}

	@Override
	public User findUser(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public List<User> findAllCandidates() {
		List<User> userList = userRepository.findAll();
		userList = userList.stream().filter(e -> e.getRole().equals(Role.CANDIDATE)).collect(Collectors.toList());
		return userList;
	}

	@Override
	public User deleteUser(Long id) throws UserNotFoundException {
		User user = findUser(id);
		if (user == null) {
			throw new UserNotFoundException();
		}
		userRepository.delete(user);
		return user;
	}

	@Override
	public User updateUser(User newUser)
			throws InvalidUserException, InvalidTimeException, InvalidAvailabilityException, UserNotFoundException {
		userHelper.validateUser(newUser);
		userHelper.validateAvailability(newUser);
		User oldUser = findUser(newUser.getId());

		oldUser.setName(newUser.getName());
		oldUser.setAvailability(newUser.getAvailability());
		userRepository.save(oldUser);

		return oldUser;
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<User> findAllInterviewers() {
		List<User> userList = userRepository.findAll();
		userList = userList.stream().filter(e -> e.getRole().equals(Role.INTERVIEWER)).collect(Collectors.toList());
		return userList;
	}

	@Override
	public Set<Availability> findAvailability(RequestInterview requestInterview)
			throws InvalidRequestInterviewException, UserNotFoundException {
		if (requestInterview == null || requestInterview.getInterviewerList() == null
				|| requestInterview.getInterviewerList().isEmpty() || requestInterview.getInterviewerList().contains(null)) {
			throw new InvalidRequestInterviewException();
		}
		Set<Availability> result = new HashSet<Availability>();
		Map<LocalDate, int[]> scheduleMap = new HashMap<LocalDate, int[]>();
		Availability availability;
		int personCount = requestInterview.getInterviewerList().size() + 1;

		// Load schedule for the candidate
		loadSchedule(scheduleMap, findUser(requestInterview.getId()).getAvailability(), true);

		// Load schedule for the interviewers
		for (Long id : requestInterview.getInterviewerList()) {
			loadSchedule(scheduleMap, findUser(id).getAvailability(), false);
		}

		for (LocalDate date : scheduleMap.keySet()) {
			int[] hours = scheduleMap.get(date);
			for (int i = 0; i < hours.length; i++) {
				if (hours[i] == personCount) {
					availability = new Availability.Builder().startTime(i).endTime(i + 1).date(date).build();
					result.add(availability);
				}
			}
		}

		return result;
	}

	public void loadSchedule(Map<LocalDate, int[]> scheduleMap, Set<Availability> availabilitySet,
			boolean isCandidate) {
		for (Availability availability : availabilitySet) {
			int[] hours = new int[23];

			if (!isCandidate) {
				hours = scheduleMap.get(availability.getDate());
			}
			if (hours != null) {
				int startTime = (int) availability.getStartTime();
				int endtime = (int) availability.getEndTime();
				while (endtime > startTime) {
					hours[startTime] = hours[startTime] + 1;
					startTime++;
				}
				scheduleMap.put(availability.getDate(), hours);
			}

		}
	}

}