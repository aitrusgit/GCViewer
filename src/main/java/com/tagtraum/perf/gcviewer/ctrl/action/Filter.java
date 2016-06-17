package com.tagtraum.perf.gcviewer.ctrl.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.tagtraum.perf.gcviewer.ctrl.GCModelLoaderController;
import com.tagtraum.perf.gcviewer.util.LocalisationHelper;
import com.tagtraum.perf.gcviewer.view.ActionCommands;
import com.tagtraum.perf.gcviewer.view.FilterDialog;
import com.tagtraum.perf.gcviewer.view.GCViewerGui;
import com.tagtraum.perf.gcviewer.view.util.ImageHelper;

public class Filter extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private final FilterDialog dialog;
	
	public Filter(GCModelLoaderController controller, GCViewerGui gui) {
		this.dialog = new FilterDialog(gui, (start, end) -> controller.filter(gui.getSelectedGCDocument(), start, end));

		putValue(NAME, LocalisationHelper.getString("main_frame_menuitem_filter"));
		putValue(SHORT_DESCRIPTION, LocalisationHelper.getString("main_frame_menuitem_hint_filter"));
		putValue(ACTION_COMMAND_KEY, ActionCommands.FILTER.toString());
		putValue(SMALL_ICON, ImageHelper.loadImageIcon("about.png"));

		setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dialog.setVisible(true);
	}

}
