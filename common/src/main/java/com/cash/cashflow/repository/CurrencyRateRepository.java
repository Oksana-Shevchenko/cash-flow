package com.cash.cashflow.repository;

import com.cash.cashflow.domain.CurrencyRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRateRepository extends CrudRepository<CurrencyRate, String> {
}
