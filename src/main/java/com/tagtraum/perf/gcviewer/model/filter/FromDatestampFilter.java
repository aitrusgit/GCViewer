package com.tagtraum.perf.gcviewer.model.filter;

import java.time.LocalDateTime;

import com.tagtraum.perf.gcviewer.model.AbstractGCEvent;

public class FromDatestampFilter implements FilterPredicate {
	
	private final LocalDateTime datestamp;

	public FromDatestampFilter(LocalDateTime datestamp) {
		this.datestamp = datestamp;
	}
	
	@Override
	public boolean matches(AbstractGCEvent<?> event) {
		return datestamp.isAfter(event.getDatestamp().toLocalDateTime());
	}
	
}
