package com.tagtraum.perf.gcviewer.model;

import java.time.ZonedDateTime;

/**
 * A view over the {@link GCModel}. It copies all the data
 * from the GCModel into a new view and recalculates all 
 * indicators again.
 * 
 * @author Claudio Fernandez <claudiof@gmail.com)
 */
public class TimeframeGCModel extends GCModel {
    
	private static final long serialVersionUID = 1L;

	private final ZonedDateTime startingOn;
    private final ZonedDateTime endingOn;
    
    public TimeframeGCModel(ZonedDateTime startingOn, ZonedDateTime endingOn, GCModel fullModel) {
    	this.startingOn = startingOn;
    	this.endingOn = endingOn;
    	
    	fullModel.getEvents().forEachRemaining(this::add);
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
    	
		return (startingOn == null || abstractEvent.getDatestamp().isAfter(startingOn)) &&
    			(endingOn == null || eventDataStamp.isBefore(endingOn));
    }
    
    @Override
    public void add(AbstractGCEvent<?> abstractEvent) {
    	if (this.isInsideTimeframe(abstractEvent)) {
        	super.add(abstractEvent);
    	}
    }
    

}
