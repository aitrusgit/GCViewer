package com.tagtraum.perf.gcviewer.ctrl.action;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import com.tagtraum.perf.gcviewer.ctrl.GCModelLoaderController;
import com.tagtraum.perf.gcviewer.util.LocalisationHelper;
import com.tagtraum.perf.gcviewer.view.ActionCommands;
import com.tagtraum.perf.gcviewer.view.FilterDialog;
import com.tagtraum.perf.gcviewer.view.GCViewerGui;
import com.tagtraum.perf.gcviewer.view.util.ImageHelper;

public class Filter extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private final FilterDialog dialog;
	private final GCModelLoaderController controller;
	
	private JDialog filterDialog;

	public Filter(GCModelLoaderController controller, Frame parent) {
		this.dialog = new FilterDialog(parent);
		this.controller = controller;

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
