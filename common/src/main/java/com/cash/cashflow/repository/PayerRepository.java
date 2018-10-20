package com.cash.cashflow.repository;

import com.cash.cashflow.domain.Payer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayerRepository extends CrudRepository<Payer, String> {
}
