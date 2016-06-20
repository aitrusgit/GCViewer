package com.tagtraum.perf.gcviewer.model;

import com.tagtraum.perf.gcviewer.model.filter.FilterPredicate;

/**
 * A view over the {@link GCModel}. It copies all the data
 * from the GCModel into a new view and recalculates all 
 * indicators again.
 * 
 * @author Claudio Fernandez (claudiof@gmail.com)
 */
public class GCFilteredModel extends GCModel {
    
	private static final long serialVersionUID = 1L;

	private FilterPredicate predicate;
	
	private GCModel fullModel;
    
    public GCFilteredModel(FilterPredicate predicate, GCModel fullModel) {
    	this.predicate = predicate;
    	
    	this.fullModel = fullModel.getUnderlyingModel();
    	this.fullModel.getEvents().forEachRemaining(this::add);
    }
   
    @Override
    public void add(AbstractGCEvent<?> abstractEvent) {
    	if (this.predicate.matches(abstractEvent)) {
        	super.add(abstractEvent);
    	}
    }
    
    public GCModel getUnderlyingModel() {
    	return this.fullModel;
    }

}
