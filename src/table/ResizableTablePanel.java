package table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

public class ResizableTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public TablePanel tablePanel;

	public ResizableTablePanel() {
		JPanel child = new JPanel(new BorderLayout());
		GridBagConstraints c = new GridBagConstraints();
		child.setBackground(Color.darkGray);

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;


		tablePanel = new TablePanel(child,c);


		Resizing newRecords = new Resizing(child);
		child.setBackground(Color.darkGray);

		this.setLayout(new BorderLayout(0, 0));

//		child.add(tablePanel, c);
//		child.setSize(new Dimension(7000, 200));
		JScrollPane scroolpane = new JScrollPane(child);
//		scroolpane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		this.add(scroolpane, BorderLayout.CENTER);

		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 0);
		newRecords.setSlider(slider);
		this.add(slider, "South");

		JSlider slider2 = new JSlider(JSlider.VERTICAL, 0, 20, 0);
		newRecords.setSlider(slider2, tablePanel.table);
		this.setSize(400, 400);
	}

}
