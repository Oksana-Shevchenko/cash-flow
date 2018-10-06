package com.cash.cashflow.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "bill_item")
public class BillItem extends BasicModel {
	@Column(name = "title")
	private String title;

	@Column(name = "amount", precision = 2)
	private BigDecimal amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bill_id", nullable = false, insertable = false)
	private Bill bill;
}
