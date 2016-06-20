package com.tagtraum.perf.gcviewer.view;

import static java.awt.GridBagConstraints.NORTH;
import static java.awt.GridBagConstraints.VERTICAL;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.BiConsumer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
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
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setLayout(new GridBagLayout());

		panel.add(new JLabel("Start: "), constraints(NORTH, VERTICAL, 0, 0));
		this.startTimeframeSpinner = new JSpinner( new SpinnerDateModel() );
		startTimeframeSpinner.setEditor(new JSpinner.DateEditor(startTimeframeSpinner, "yyyy-MM-dd'T'HH:mm:ss.SSS"));
		panel.add(startTimeframeSpinner, constraints(NORTH, VERTICAL, 1, 0));

		panel.add(new JLabel("End:"), constraints(NORTH, VERTICAL, 0, 1));
		this.endTimeframeSpinner = new JSpinner( new SpinnerDateModel() );
		endTimeframeSpinner.setEditor(new JSpinner.DateEditor(endTimeframeSpinner, "yyyy-MM-dd'T'HH:mm:ss.SSS"));
		panel.add(endTimeframeSpinner, constraints(NORTH, VERTICAL, 1, 1));

		Panel buttonPanel = new Panel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton cancelButton = new JButton(LocalisationHelper.getString("button_cancel"));
		cancelButton.addActionListener(this);
		buttonPanel.add(cancelButton);

		JButton filterButton = new JButton(LocalisationHelper.getString("button_ok"));
		filterButton.setActionCommand(ACTION_OK);
		filterButton.addActionListener(this);
		buttonPanel.add(filterButton);

		panel.add(buttonPanel, constraints(NORTH, VERTICAL, 1, 2));

		this.setContentPane(panel);

		this.pack();
		setResizable(false);
		setVisible(false);
	}

	private GridBagConstraints constraints(int anchor, int fill, int x, int y) {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = anchor;
        gridBagConstraints.fill = fill;
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        return gridBagConstraints;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (ACTION_OK.equals(e.getActionCommand())) {
			this.performFilter();
			setVisible(false);
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
