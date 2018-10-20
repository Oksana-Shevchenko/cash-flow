package com.cash.cashflow.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BillItemRequest {
	private String title;
	private BigDecimal amount;
	private String categoryId;
}
