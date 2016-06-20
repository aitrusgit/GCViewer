package com.tagtraum.perf.gcviewer.model.filter;

import com.tagtraum.perf.gcviewer.model.AbstractGCEvent;

class AndFilter implements FilterPredicate {

	private final FilterPredicate left;
	private final FilterPredicate right;
	
	AndFilter(FilterPredicate left, FilterPredicate right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public boolean matches(AbstractGCEvent<?> event) {
		return this.left.matches(event) && this.right.matches(event);
	}
	
}
