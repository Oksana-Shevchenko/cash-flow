package com.cash.cashflow.domain.projection;

import com.cash.cashflow.domain.Payer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "payerProjection", types = {Payer.class})
public interface PayerProjection {

	String getId();

	BigDecimal getAmount();

	@Value("#{target.user.id}")
	String getUserId();

}
