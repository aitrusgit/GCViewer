package com.tagtraum.perf.gcviewer.ctrl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import com.tagtraum.perf.gcviewer.model.GCResource;
import com.tagtraum.perf.gcviewer.view.GCDocument;

/**
 * Controller class for {@link GCModelLoader}.
 *
 * @author <a href="mailto:gcviewer@gmx.ch">Joerg Wuethrich</a>
 * <p>created on: 16.08.2014</p>
 */
public interface GCModelLoaderController {
    void add(File[] files);

    void add(GCResource gcResource);

    void add(List<GCResource> gcResourceList);

    void open(File[] files);

    /**
     * Open a gc log resource from a filename or URL.
     *
     * @param gcResource filename or URL name.
     */
    void open(GCResource gcResource);

    void open(List<GCResource> gcResourceList);

    /**
     * Reload all models of <code>gcDocument</code> and provide tracker. The tracker will
     * fire a propertyChangeEvent, as soon as all GCModelLoaders have finished loading.
     *
     * @param gcDocument document of which models should be reloaded
     * @return tracker to track finish state of all models being loaded
     */
    GCModelLoaderGroupTracker reload(GCDocument gcDocument);
    
    /**
     * Filters all events in the model using the given boundaries
     */
    void filter(GCDocument gcDocument, LocalDateTime start, LocalDateTime end);
}
