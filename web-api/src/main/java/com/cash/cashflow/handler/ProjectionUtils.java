package com.cash.cashflow.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

@Component
public class ProjectionUtils {

	@Autowired
	private ProjectionFactory projectionFactory;

	public <T, P> Resource<P> toResource(final T entity, final Class<P> projectionClass) {
		if (entity == null) {
			throw new IllegalArgumentException("Entity to project is null");
		}
		return new Resource<>(projectionFactory.createProjection(projectionClass, entity));
	}
}
