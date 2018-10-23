package com.cash.cashflow.domain.projection;

import com.cash.cashflow.domain.BillItem;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "billItemProjection", types = {BillItem.class})
public interface BillItemProjection {

	String getId();

	String getTitle();

	BigDecimal getAmount();

	CategoryProjection getCategory();
}
