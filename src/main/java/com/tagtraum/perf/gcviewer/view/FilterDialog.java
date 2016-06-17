package com.tagtraum.perf.gcviewer.view;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import com.tagtraum.perf.gcviewer.util.LocalisationHelper;
import com.tagtraum.perf.gcviewer.view.util.UrlDisplayHelper;

public class FilterDialog extends ScreenCenteredDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	public FilterDialog(Frame f) {
		super(f, LocalisationHelper.getString("filter_dialog_title"));

		final JPanel panel = new JPanel();
		JSpinner startTimeframeSpinner = new JSpinner( new SpinnerDateModel() );
		startTimeframeSpinner.setEditor(new JSpinner.DateEditor(startTimeframeSpinner, "yyyy-MM-dd HH:mm:ss"));
		panel.add(startTimeframeSpinner);

		JSpinner endTimeframeSpinner = new JSpinner( new SpinnerDateModel() );
		endTimeframeSpinner.setEditor(new JSpinner.DateEditor(endTimeframeSpinner, "yyyy-MM-dd HH:mm:ss"));
		panel.add(endTimeframeSpinner);
		
		Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton cancelButton = new JButton(LocalisationHelper.getString("button_ok"));
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton);

        JButton filterButton = new JButton(LocalisationHelper.getString("button_ok"));
        filterButton.setActionCommand(ACTION_OK);
        filterButton.addActionListener(this);
        buttonPanel.add(filterButton);
        
        panel.add(buttonPanel);

		this.setContentPane(panel);

		this.pack();
		setResizable(false);
        setVisible(false);
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ACTION_OK.equals(e.getActionCommand())) {
        	this.performFilter();
        } else {
            // default action
            super.actionPerformed(e);
        }
    }

	private void performFilter() {
		
	}
	
}
