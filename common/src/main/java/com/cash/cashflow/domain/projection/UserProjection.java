package com.cash.cashflow.domain.projection;

import com.cash.cashflow.domain.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "userProjection", types = {User.class})
public interface UserProjection {
	String getId();

	String getName();

	String getEmail();
}
