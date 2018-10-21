package com.cash.cashflow.validator;

import com.cash.cashflow.model.BillRequest;

public interface BillRequestValidator {
	void validateBillRequest(BillRequest request);
}
