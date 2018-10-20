package com.cash.cashflow.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PayerRequest {
	private String userId;
	private BigDecimal amount;
}
