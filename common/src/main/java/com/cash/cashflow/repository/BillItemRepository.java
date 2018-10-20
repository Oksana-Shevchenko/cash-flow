package com.cash.cashflow.repository;

import com.cash.cashflow.domain.BillItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillItemRepository extends CrudRepository<BillItem, String> {
}
