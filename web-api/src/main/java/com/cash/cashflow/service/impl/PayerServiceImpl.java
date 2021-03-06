package com.cash.cashflow.service.impl;

import com.cash.cashflow.domain.Bill;
import com.cash.cashflow.domain.Payer;
import com.cash.cashflow.model.PayerRequest;
import com.cash.cashflow.repository.PayerRepository;
import com.cash.cashflow.service.PayerService;
import com.cash.cashflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PayerServiceImpl implements PayerService {
	@Autowired
	private PayerRepository payerRepository;

	@Autowired
	private UserService userService;

	@Override
	public List<Payer> createPayers(List<PayerRequest> request, Bill bill) {
		List<Payer> payers = request.stream().map(payerRequest -> this.createPayer(payerRequest, bill)).collect(Collectors.toList());
		bill.setPayers(payers);
		return payers;
	}

	private Payer createPayer(PayerRequest request, Bill bill) {
		Payer payer = Payer.builder()
				.amount(request.getAmount())
				.user(userService.findUserById(request.getUserId()))
				.bill(bill)
				.build();
		return payerRepository.save(payer);
	}
}
