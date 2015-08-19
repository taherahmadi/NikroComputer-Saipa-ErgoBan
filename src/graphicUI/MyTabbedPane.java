/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphicUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MyTabbedPane {

	private static JPanel getTitlePanel(final JTabbedPane tabbedPane,
			final JPanel panel, String title) {
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		titlePanel.setOpaque(false);
		JLabel titleLbl = new JLabel(title);
		titleLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		titlePanel.add(titleLbl);
		JButton closeButton = new JButton("x");

		closeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.remove(panel);
			}
		});
		titlePanel.add(closeButton);

		return titlePanel;
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	public static void createAndShowGUI() {
		JFrame frame = new JFrame("Tabs");
		frame.setMinimumSize(new Dimension(500, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		tabbedPane.add(panel);
		tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(panel),
				getTitlePanel(tabbedPane, panel, "Tab1"));

		JPanel panel1 = new JPanel();
		panel1.setOpaque(false);
		tabbedPane.add(panel1);
		tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(panel1),
				getTitlePanel(tabbedPane, panel1, "Tab2"));

		JPanel panel2 = new JPanel();
		panel2.setOpaque(false);
		tabbedPane.add(panel2);
		tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(panel2),
				getTitlePanel(tabbedPane, panel2, "Tab3"));

		JPanel panel3 = new JPanel();
		panel3.setOpaque(false);
		tabbedPane.add(panel3);
		tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(panel3),
				getTitlePanel(tabbedPane, panel3, "Tab4"));

		frame.add(tabbedPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MyTabbedPane.createAndShowGUI();
			}
		});
	}

}

// I have erased the imports in order to make it shorter. Now we need a driver
// class.

