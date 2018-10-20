package com.cash.cashflow.service;

import com.cash.cashflow.domain.Bill;
import com.cash.cashflow.domain.Share;
import com.cash.cashflow.model.ShareRequest;

import java.util.List;

public interface ShareService {
	List<Share> createShares(List<ShareRequest> request, Bill bill);
}
