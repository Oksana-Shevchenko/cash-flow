package com.cash.cashflow.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bill")
public class Bill extends BasicModel {
	@Column(name = "description")
	private String description;

	@Column(name = "amount", precision = 2)
	private BigDecimal amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "currency_code", referencedColumnName = "code")
	private Currency currency;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id", referencedColumnName = "id")
	private Group group;

	@OneToMany(mappedBy = "bill", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Payer> payers;

	@OneToMany(mappedBy = "bill", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BillItem> items;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_bill",
			joinColumns = {@JoinColumn(name = "bill_id", nullable = false, updatable = false)},
			inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)})
	private List<User> participants;

	@Enumerated(EnumType.STRING)
	@Column(name = "shared_type")
	private SharedType sharedType;

	@OneToMany(mappedBy = "bill", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Share> shares;
}
