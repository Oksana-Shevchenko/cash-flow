package com.cash.cashflow.domain.projection;

import com.cash.cashflow.domain.Bill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;
import java.util.List;

@Projection(name = "billProjection", types = {Bill.class})
public interface BillProjection {

	String getId();

	BigDecimal getAmount();

	@Value("#{target.currency == null ? 'N/A' : target.currency.code}")
	String getCurrency();

	List<PayerProjection> getPayers();

}
