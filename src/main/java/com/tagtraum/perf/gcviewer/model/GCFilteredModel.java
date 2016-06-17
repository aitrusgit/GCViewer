package com.tagtraum.perf.gcviewer.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * A view over the {@link GCModel}. It copies all the data
 * from the GCModel into a new view and recalculates all 
 * indicators again.
 * 
 * @author Claudio Fernandez <claudiof@gmail.com)
 */
public class GCFilteredModel extends GCModel {
    
	private static final long serialVersionUID = 1L;

	private final LocalDateTime startingOn;
    private final LocalDateTime endingOn;

	private GCModel fullModel;
    
    public GCFilteredModel(LocalDateTime startingOn, LocalDateTime endingOn, GCModel fullModel) {
    	this.startingOn = startingOn;
    	this.endingOn = endingOn;

    	this.fullModel = fullModel.getUnderlyingModel();
    	this.fullModel.getEvents().forEachRemaining(this::add);
    }
    
    /**
     * @return true if the event provided is inside the timeframe configured by
     * startingOn and endingOn attributes.
     */
    private boolean isInsideTimeframe(AbstractGCEvent<?> abstractEvent) {
    	ZonedDateTime eventDataStamp = abstractEvent.getDatestamp();
    	if (eventDataStamp == null) {
    		//can't do much with absolute times if there is no absolute time information
    		//available
    		return true;
    	}
    	
		LocalDateTime localEventDateStamp = eventDataStamp.toLocalDateTime();
		return (startingOn == null || localEventDateStamp.isAfter(startingOn)) &&
    			(endingOn == null || localEventDateStamp.isBefore(endingOn));
    }
    
    @Override
    public void add(AbstractGCEvent<?> abstractEvent) {
    	if (this.isInsideTimeframe(abstractEvent)) {
        	super.add(abstractEvent);
    	}
    }
    
    public GCModel getUnderlyingModel() {
    	return this.fullModel;
    }

}
