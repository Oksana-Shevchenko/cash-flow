package com.cash.cashflow.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "balance")
public class Balance extends BasicModel {

	@Column(name = "amount", precision = 2)
	private BigDecimal amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "currency_code", referencedColumnName = "code")
	private Currency currency;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id", referencedColumnName = "id")
	private Group group;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
}
