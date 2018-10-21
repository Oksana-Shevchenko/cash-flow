package com.cash.cashflow.service.impl;

import com.cash.cashflow.domain.Bill;
import com.cash.cashflow.model.BillRequest;
import com.cash.cashflow.repository.BillRepository;
import com.cash.cashflow.service.*;
import com.cash.cashflow.validator.BillRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BillServiceImpl implements BillService {
	@Autowired
	private BillRepository billRepository;

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private UserService userService;

	@Autowired
	private PayerService payerService;

	@Autowired
	private BillItemService billItemService;

	@Autowired
	private ShareService shareService;

	@Autowired
	private BillRequestValidator billRequestValidator;

	@Override
	public Bill createBill(BillRequest request) {

		billRequestValidator.validateBillRequest(request);

		Bill bill = Bill.builder()
				.description(request.getDescription())
				.amount(request.getAmount())
				.currency(currencyService.findCurrencyById(request.getCurrencyCode()))
				.group(groupService.findGroupById(request.getGroupId()))
				.participants(userService.findAllUserById(request.getParticipants()))
				.sharedType(request.getSharedType())
				.build();

		Bill savedBill = billRepository.save(bill);

		payerService.createPayers(request.getPayers(), savedBill);
		billItemService.createBillItems(request.getItems(), savedBill);
		shareService.createShares(request.getShares(), savedBill);

		return savedBill;
	}
}
