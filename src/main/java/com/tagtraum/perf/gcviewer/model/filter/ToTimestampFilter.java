package com.tagtraum.perf.gcviewer.model.filter;

import com.tagtraum.perf.gcviewer.model.AbstractGCEvent;

public class ToTimestampFilter implements FilterPredicate {
	
	private final double timestamp;

	public ToTimestampFilter(double timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public boolean matches(AbstractGCEvent<?> event) {
		return event.getTimestamp() <= timestamp;
	}
	
}
