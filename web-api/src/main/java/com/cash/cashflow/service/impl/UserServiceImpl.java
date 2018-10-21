package com.cash.cashflow.service.impl;

import com.cash.cashflow.domain.User;
import com.cash.cashflow.exception.ApiException;
import com.cash.cashflow.repository.UserRepository;
import com.cash.cashflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserById(String id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
						"User Not Found",
						"user.not.found"));
	}

	@Override
	public List<User> findAllUserById(Set<String> ids) {
		List<User> participants = (List<User>) userRepository.findAllById(ids);
		if (participants.size() != ids.size()) {
			throw new ApiException(HttpStatus.NOT_FOUND, "User not found", "user.not.found");
		}
		return participants;
	}
}
