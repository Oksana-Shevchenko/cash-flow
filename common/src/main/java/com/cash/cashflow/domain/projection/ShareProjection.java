package com.cash.cashflow.domain.projection;

import com.cash.cashflow.domain.Share;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "shareProjection", types = {Share.class})
public interface ShareProjection {
	BigDecimal getAmount();

	BigDecimal getPercent();

	BigDecimal getShare();
}
