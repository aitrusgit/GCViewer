package com.tagtraum.perf.gcviewer.ctrl.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.tagtraum.perf.gcviewer.ctrl.GCModelLoaderController;
import com.tagtraum.perf.gcviewer.model.filter.FilterPredicate;
import com.tagtraum.perf.gcviewer.model.filter.FromDatestampFilter;
import com.tagtraum.perf.gcviewer.model.filter.ToDatestampFilter;
import com.tagtraum.perf.gcviewer.util.LocalisationHelper;
import com.tagtraum.perf.gcviewer.view.ActionCommands;
import com.tagtraum.perf.gcviewer.view.FilterDialog;
import com.tagtraum.perf.gcviewer.view.GCViewerGui;
import com.tagtraum.perf.gcviewer.view.util.ImageHelper;

public class Filter extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private final FilterDialog dialog;

	private GCViewerGui gui;
	
	public Filter(GCModelLoaderController controller, GCViewerGui gui) {
		
		this.gui = gui;
		this.dialog = new FilterDialog(gui, (start, end) -> {
			FilterPredicate predicate = new FromDatestampFilter(start).and(new ToDatestampFilter(end));
			controller.filter(gui.getSelectedGCDocument(), predicate);
		});

		putValue(NAME, LocalisationHelper.getString("main_frame_menuitem_filter"));
		putValue(SHORT_DESCRIPTION, LocalisationHelper.getString("main_frame_menuitem_hint_filter"));
		putValue(ACTION_COMMAND_KEY, ActionCommands.FILTER.toString());
		putValue(SMALL_ICON, ImageHelper.loadImageIcon("about.png"));

		setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/* List<GCResource> resources = this.gui.getSelectedGCDocument().getGCResources();
		ZonedDateTime start = resources.stream().map(r -> r.getModel().getFirstDateStamp()).sorted().findFirst().get();
		ZonedDateTime end = resources.stream().map(r -> r.getModel().getLastDateStamp()).collect(Collectors.minBy(a, b -> a.));
		this.dialog.setDefautls(start, end); */
		
		this.dialog.setVisible(true);
	}

}
