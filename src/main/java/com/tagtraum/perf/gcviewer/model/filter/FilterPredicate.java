package com.tagtraum.perf.gcviewer.model.filter;

import com.tagtraum.perf.gcviewer.model.AbstractGCEvent;
import com.tagtraum.perf.gcviewer.model.GCEvent;

/**
 * A filter to test if a certain {@link GCEvent} shoudl 
 * be taken into account or not
 * 
 * @author Claudio Fernandez (claudiof@gmail.com)
 */
public interface FilterPredicate {
	
	boolean matches(AbstractGCEvent<?> event);
	
	default public FilterPredicate and(FilterPredicate other) {
		return new AndFilter(this, other);
	}
	
}
