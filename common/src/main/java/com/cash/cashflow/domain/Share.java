package com.cash.cashflow.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@Table(name = "share")
public class Share extends BasicModel {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bill_id")//, referencedColumnName = "id")
	private Bill bill;

	@Column(name = "amount", precision = 2)
	private BigDecimal amount;

	@Column(name = "percent", precision = 2)
	private BigDecimal percent;

	@Column(name = "share", precision = 2)
	private BigDecimal share;
}
