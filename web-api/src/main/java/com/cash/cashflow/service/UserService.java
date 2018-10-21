package com.cash.cashflow.service;

import com.cash.cashflow.domain.User;

import java.util.List;
import java.util.Set;

public interface UserService {
	User findUserById(String id);
	List<User> findAllUserById(Set<String> ids);
}
