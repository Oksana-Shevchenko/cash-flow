package com.cash.cashflow.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "category")
public class Category extends BasicModel {
	@Column(name = "name")
	private String name;
}
