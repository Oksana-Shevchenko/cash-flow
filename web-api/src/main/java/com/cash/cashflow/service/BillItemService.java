package com.cash.cashflow.service;

import com.cash.cashflow.domain.Bill;
import com.cash.cashflow.domain.BillItem;
import com.cash.cashflow.model.BillItemRequest;

import java.util.List;

public interface BillItemService {
	List<BillItem> createBillItems(List<BillItemRequest> request, Bill bill);
}
