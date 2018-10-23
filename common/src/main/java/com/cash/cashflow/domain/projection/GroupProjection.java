package com.cash.cashflow.domain.projection;

import com.cash.cashflow.domain.Group;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "groupProjection", types = {Group.class})
public interface GroupProjection {
	String getId();

	String getName();
}
