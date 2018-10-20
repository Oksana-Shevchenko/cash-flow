package com.cash.cashflow.service;

import com.cash.cashflow.domain.Bill;
import com.cash.cashflow.model.BillRequest;

public interface BillService {
	Bill createBill(BillRequest request);
}
