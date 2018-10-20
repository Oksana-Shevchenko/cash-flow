package com.cash.cashflow.service.impl;

import com.cash.cashflow.domain.Bill;
import com.cash.cashflow.domain.User;
import com.cash.cashflow.model.BillRequest;
import com.cash.cashflow.repository.BillRepository;
import com.cash.cashflow.repository.CurrencyRepository;
import com.cash.cashflow.repository.GroupRepository;
import com.cash.cashflow.repository.UserRepository;
import com.cash.cashflow.service.BillItemService;
import com.cash.cashflow.service.BillService;
import com.cash.cashflow.service.PayerService;
import com.cash.cashflow.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BillServiceImpl implements BillService {
	@Autowired
	private BillRepository billRepository;

	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PayerService payerService;

	@Autowired
	private BillItemService billItemService;

	@Autowired
	private ShareService shareService;

	@Override
	public Bill createBill(BillRequest request) {
		List<User> participants = (List<User>) userRepository.findAllById(request.getParticipants());

		if (participants.size() != request.getParticipants().size()) {
			throw new RuntimeException("User not found");
		}

		Bill bill = Bill.builder()
				.description(request.getDescription())
				.amount(request.getAmount())
				.currency(currencyRepository.findById(request.getCurrencyCode()).orElseThrow(() -> new RuntimeException("Currency Not Found")))
				.group(groupRepository.findById(request.getGroupId()).orElseThrow(() -> new RuntimeException("Currency Not Found")))
				.participants(participants)
				.sharedType(request.getSharedType())
				.build();

		Bill savedBill = billRepository.save(bill);

		payerService.createPayers(request.getPayers(), savedBill);
		billItemService.createBillItems(request.getItems(), savedBill);
		shareService.createShares(request.getShares(), savedBill);

		return savedBill;
	}
}
