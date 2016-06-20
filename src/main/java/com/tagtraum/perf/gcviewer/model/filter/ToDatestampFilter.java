package com.tagtraum.perf.gcviewer.model.filter;

import java.time.LocalDateTime;

import com.tagtraum.perf.gcviewer.model.AbstractGCEvent;

public class ToDatestampFilter implements FilterPredicate {
	
	private final LocalDateTime datestamp;

	public ToDatestampFilter(LocalDateTime datestamp) {
		this.datestamp = datestamp;
	}
	
	@Override
	public boolean matches(AbstractGCEvent<?> event) {
		return datestamp.isBefore(event.getDatestamp().toLocalDateTime());
	}
	
}
