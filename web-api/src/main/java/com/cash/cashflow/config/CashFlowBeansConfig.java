package com.cash.cashflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.stereotype.Component;

@Component
public class CashFlowBeansConfig {

	@Bean
	public ProjectionFactory projectionFactory() {
		return new SpelAwareProxyProjectionFactory();
	}

}
