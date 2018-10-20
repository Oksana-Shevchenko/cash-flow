package com.cash.cashflow.service.impl;

import com.cash.cashflow.domain.Bill;
import com.cash.cashflow.domain.BillItem;
import com.cash.cashflow.model.BillItemRequest;
import com.cash.cashflow.repository.BillItemRepository;
import com.cash.cashflow.repository.CategoryRepository;
import com.cash.cashflow.service.BillItemService;
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
	private CategoryRepository categoryRepository;

	@Override
	public List<BillItem> createBillItems(List<BillItemRequest> request, Bill bill) {
		return request.stream().map(createBillRequest -> this.createBillItem(createBillRequest, bill)).collect(Collectors.toList());
	}

	private BillItem createBillItem(BillItemRequest request, Bill bill) {
		BillItem billItem = BillItem.builder()
				.amount(request.getAmount())
				.title(request.getTitle())
				.category(categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found")))
				.bill(bill)
				.build();
		return billItemRepository.save(billItem);
	}
}
