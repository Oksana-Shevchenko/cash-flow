package com.cash.cashflow.service;

import com.cash.cashflow.domain.Bill;
import com.cash.cashflow.domain.Payer;
import com.cash.cashflow.model.PayerRequest;

import java.util.List;

public interface PayerService {
	List<Payer> createPayers(List<PayerRequest> request, Bill bill);
}
