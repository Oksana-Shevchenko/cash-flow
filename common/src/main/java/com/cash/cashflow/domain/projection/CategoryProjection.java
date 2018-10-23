package com.cash.cashflow.domain.projection;

import com.cash.cashflow.domain.Category;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "categoryProjection", types = {Category.class})
public interface CategoryProjection {
	String getId();

	String getName();
}
