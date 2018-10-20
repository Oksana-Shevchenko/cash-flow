package com.cash.cashflow.repository;

import com.cash.cashflow.domain.Share;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareRepository extends CrudRepository<Share, String> {
}
