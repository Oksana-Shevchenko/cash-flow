package com.cash.cashflow.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name = "payer")
public class Payer extends BasicModel {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(name = "amount", precision = 2)
	private BigDecimal amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bill_id")
	private Bill bill;
}
