package com.cash.cashflow.service;

import com.cash.cashflow.domain.Currency;

public interface CurrencyService {
	Currency findCurrencyById(String id);
}
