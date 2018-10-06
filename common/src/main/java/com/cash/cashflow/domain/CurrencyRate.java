package com.cash.cashflow.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "currency_rate")
public class CurrencyRate extends BasicModel {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "currency_from_code", referencedColumnName = "code")
	private Currency currencyFrom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "currency_to_code", referencedColumnName = "code")
	private Currency currencyTo;

	@Column(name = "rate", precision = 2)
	private BigDecimal rate;
}
