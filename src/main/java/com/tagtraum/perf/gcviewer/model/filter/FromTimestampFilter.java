package com.tagtraum.perf.gcviewer.model.filter;

import com.tagtraum.perf.gcviewer.model.AbstractGCEvent;

public class FromTimestampFilter implements FilterPredicate {
	
	private final double timestamp;

	public FromTimestampFilter(double timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public boolean matches(AbstractGCEvent<?> event) {
		return timestamp >= event.getTimestamp();
	}
	
}
