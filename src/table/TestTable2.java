/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TestTable2 {

	private String search;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	private class TableSearchRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = -2325081525996680730L;

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			setBackground(null);
			Component tableCellRendererComponent = super
					.getTableCellRendererComponent(table, value, isSelected,
							hasFocus, row, column);
			if (getSearch() != null && getSearch().length() > 0
					&& value.toString().contains(getSearch())) {
				for (int i = 0; i < table.getColumnCount(); i++) {
					setBackground(Color.RED);
				}
			}
			return tableCellRendererComponent;
		}
	}

	protected void initUI() {
		DefaultTableModel model = new DefaultTableModel();
		for (int i = 0; i < 5; i++) {
			model.addColumn("ass " + (i + 1));
		}
		Random random = new Random();
		for (int i = 0; i < 200; i++) {
			Vector<Object> row = new Vector<Object>();
			for (int j = 0; j < 40; j++) {
				row.add(WORDS[random.nextInt(WORDS.length)]);
			}
			model.addRow(row);
		}
		table = new JTable(model);
		TableSearchRenderer renderer = new TableSearchRenderer();
		table.setDefaultRenderer(Object.class, renderer);
		textField = new JTextField(30);
		textField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateSearch();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateSearch();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateSearch();
			}
		});
		JFrame frame = new JFrame(TestTable2.class.getSimpleName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.add(scrollpane, BorderLayout.CENTER);
		frame.add(textField, BorderLayout.NORTH);
		frame.setSize(1000, 800);
		frame.setVisible(true);
	}

	protected void updateSearch() {
		setSearch(textField.getText());
		table.repaint();
	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new TestTable2().initUI();
			}
		});
	}

	private static final String[] WORDS = { "art", "australia", "baby",
			"beach", "birthday", "blue", "bw", "california", "canada", "canon",
			"cat", "chicago", "china", "christmas", "city", "dog", "england",
			"europe", "family", "festival", "flower", "flowers", "food",
			"france", "friends", "fun", "germany", "holiday", "india", "italy",
			"japan", "london", "me", "mexico", "music", "nature", "new",
			"newyork", "night", "nikon", "nyc", "paris", "park", "party",
			"people", "portrait", "sanfrancisco", "sky", "snow", "spain",
			"summer", "sunset", "taiwan", "tokyo", "travel", "trip", "uk",
			"usa", "vacation", "water", "wedding" };
	private JTable table;
	private JTextField textField;

}