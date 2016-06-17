package com.tagtraum.perf.gcviewer.view;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.function.BiConsumer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import com.tagtraum.perf.gcviewer.util.LocalisationHelper;

public class FilterDialog extends ScreenCenteredDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private final JSpinner startTimeframeSpinner;
	private final JSpinner endTimeframeSpinner;

	private final BiConsumer<LocalDateTime, LocalDateTime> onAction;

	public FilterDialog(Frame f, BiConsumer<LocalDateTime, LocalDateTime> onAction) {
		super(f, LocalisationHelper.getString("filter_dialog_title"));
		this.onAction = onAction;

		final JPanel panel = new JPanel();
		this.startTimeframeSpinner = new JSpinner( new SpinnerDateModel() );
		startTimeframeSpinner.setEditor(new JSpinner.DateEditor(startTimeframeSpinner, "yyyy-MM-dd'T'HH:mm:ss.SSS"));
		panel.add(startTimeframeSpinner);

		this.endTimeframeSpinner = new JSpinner( new SpinnerDateModel() );
		endTimeframeSpinner.setEditor(new JSpinner.DateEditor(endTimeframeSpinner, "yyyy-MM-dd'T'HH:mm:ss.SSS"));
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
		LocalDateTime startDate = this.getDateTime(this.startTimeframeSpinner);
		LocalDateTime endDate = this.getDateTime(this.endTimeframeSpinner);
		this.onAction.accept(startDate, endDate);
	}
	
	private LocalDateTime getDateTime(JSpinner spinner) {
		Date startDate = (Date) spinner.getValue();
		return LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
	}
	
}
