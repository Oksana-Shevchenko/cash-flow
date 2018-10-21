package com.cash.cashflow.service.impl;

import com.cash.cashflow.domain.Bill;
import com.cash.cashflow.domain.BillItem;
import com.cash.cashflow.model.BillItemRequest;
import com.cash.cashflow.repository.BillItemRepository;
import com.cash.cashflow.service.BillItemService;
import com.cash.cashflow.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BillItemServiceImpl implements BillItemService {
	@Autowired
	private BillItemRepository billItemRepository;

	@Autowired
	private CategoryService categoryService;

	@Override
	public List<BillItem> createBillItems(List<BillItemRequest> request, Bill bill) {
		List<BillItem> items = request.stream()
				.map(createBillRequest -> this.createBillItem(createBillRequest, bill))
				.collect(Collectors.toList());
		bill.setItems(items);
		return items;
	}

	private BillItem createBillItem(BillItemRequest request, Bill bill) {
		BillItem billItem = BillItem.builder()
				.amount(request.getAmount())
				.title(request.getTitle())
				.category(categoryService.findCategoryById(request.getCategoryId()))
				.bill(bill)
				.build();
		return billItemRepository.save(billItem);
	}
}
