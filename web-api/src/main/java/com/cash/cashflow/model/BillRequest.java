package com.cash.cashflow.model;

import com.cash.cashflow.domain.SharedType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class BillRequest {
	private String description;
	private BigDecimal amount;
	private String currencyCode;
	private String groupId;
	private List<PayerRequest> payers;
	private List<BillItemRequest> items;
	private Set<String> participants;
	private SharedType sharedType;
	private List<ShareRequest> shares;
}
