package com.cash.cashflow.service.impl;

import com.cash.cashflow.domain.Group;
import com.cash.cashflow.exception.ApiException;
import com.cash.cashflow.repository.GroupRepository;
import com.cash.cashflow.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupRepository groupRepository;

	@Override
	public Group findGroupById(String id) {
		return groupRepository.findById(id)
				.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
						"Group Not Found",
						"group.not.found"));
	}
}
