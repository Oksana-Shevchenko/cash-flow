package com.cash.cashflow.service.impl;

import com.cash.cashflow.domain.Currency;
import com.cash.cashflow.exception.ApiException;
import com.cash.cashflow.repository.CurrencyRepository;
import com.cash.cashflow.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CurrencyServiceImpl implements CurrencyService {
	@Autowired
	private CurrencyRepository currencyRepository;

	@Override
	public Currency findCurrencyById(String id) {
		return currencyRepository.findById(id)
				.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
						"Currency Not Found",
						"currency.not.found"));
	}
}
