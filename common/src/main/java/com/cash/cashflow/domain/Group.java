package com.cash.cashflow.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "groups")
public class Group extends BasicModel {
	@Column(name = "name")
	private String name;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_group",
			joinColumns = {@JoinColumn(name = "group_id", nullable = false, updatable = false)},
			inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)})
	private List<User> participants;
}
