package com.cash.cashflow.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User extends BasicModel {
	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private List<Balance> balance;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_group",
			joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
			inverseJoinColumns = {@JoinColumn(name = "group_id", nullable = false, updatable = false)})
	private List<Group> groups;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_bill",
			joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
			inverseJoinColumns = {@JoinColumn(name = "bill_id", nullable = false, updatable = false)})
	private List<Bill> bills;
}
