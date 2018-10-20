package com.cash.cashflow.service.impl;

import com.cash.cashflow.domain.Bill;
import com.cash.cashflow.domain.Share;
import com.cash.cashflow.model.ShareRequest;
import com.cash.cashflow.repository.ShareRepository;
import com.cash.cashflow.repository.UserRepository;
import com.cash.cashflow.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ShareServiceImpl implements ShareService {
	@Autowired
	private ShareRepository shareRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Share> createShares(List<ShareRequest> request, Bill bill) {
		return request.stream().map(shareRequest -> this.createShare(shareRequest, bill)).collect(Collectors.toList());
	}

	private Share createShare(ShareRequest request, Bill bill) {
		Share share = Share.builder()
				.user(userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found")))
				.bill(bill)
				.amount(request.getAmount())
				.percent(request.getPercent())
				.share(request.getShare())
				.build();
		return shareRepository.save(share);
	}
}
