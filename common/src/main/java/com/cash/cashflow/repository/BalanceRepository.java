package com.cash.cashflow.repository;

import com.cash.cashflow.domain.Balance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends CrudRepository<Balance, String> {
}
