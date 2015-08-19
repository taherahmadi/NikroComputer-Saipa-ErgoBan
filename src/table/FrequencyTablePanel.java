/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.awt.Component;
import java.awt.ComponentOrientation;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;

import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import org.jfree.chart.encoders.EncoderUtil;
import org.jfree.chart.encoders.ImageFormat;

/**
 * TableDemo is just like SimpleTableDemo, except that it uses a custom
 * TableModel.
 */
public class FrequencyTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public MyTableModel tableModel;
	public JTable table;
	public ArrayList<Object[]> data;
	int[] alignments = new int[] { JLabel.LEFT, JLabel.RIGHT, JLabel.RIGHT };

	public FrequencyTablePanel(String[] datas, String[] values) {
		super(new GridLayout(1, 0));
		super.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		data = new ArrayList<>();
		tableModel = new MyTableModel(data);
		table = new JTable(tableModel);
		table.setRowHeight(40);
		// table.getColumnModel().getColumn(0).setResizable(false);
		// table.getColumnModel().getColumn(0).setMaxWidth(120);
		// table.getColumnModel().getColumn(1).setResizable(false);
		// table.getColumnModel().getColumn(1).setMaxWidth(120);
		table.setDefaultRenderer(Object.class, new MyTableCellRenderer2());
		for (int i = 0; i < tableModel.getColumnCount(); i++) {
			table.getTableHeader()
					.getColumnModel()
					.getColumn(i)
					.setHeaderRenderer(new HeaderRenderer(table, alignments[1]));
			// table.getColumnModel().getColumn(i).setCellRenderer(new
			// MyTableCellRenderer());
		}
		table.getTableHeader().setReorderingAllowed(false);
		table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		// table.setPreferredScrollableViewportSize(new Dimension(10000, 200));

		// Create the scroll pane and add the table to it.
		for (int i = 0; i < datas.length; i++) {
			String[] a = new String[2];
			a[0] = datas[i];
			a[1] = values[i];
			this.tableModel.addRow(a);
		}
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(7000, 20));
		// scrollPane.setHorizontalScrollBar(new
		// JScrollBar(JScrollBar.HORIZONTAL));
		// Add the scroll pane to this panel.
		add(scrollPane);
	}

	public void addRow() {
		tableModel.addRow();

	}

	public String doSaveAs(File f) throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"PDF Files", "pdf");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		String filename = "";
		int option = fileChooser.showSaveDialog(this);
		if (option == JFileChooser.APPROVE_OPTION) {
			filename = fileChooser.getSelectedFile().getPath();
			if (!filename.endsWith(".png")) {
				filename = filename + ".png";
			}
			saveTableAsPNG(new File(filename), this.table, getWidth(),
					getHeight());
		}
		return filename;
	}

	private void saveTableAsPNG(File file, JTable table, int width, int height)
			throws FileNotFoundException, IOException {
		OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
		try {
			writeTableAsPNG(out, table, width, height);
		} finally {
			out.close();
		}
	}

	private void writeTableAsPNG(OutputStream out, JTable table, int width,
			int height) throws IOException {
		BufferedImage bufferedImage = createImage(table);
		EncoderUtil.writeBufferedImage(bufferedImage, ImageFormat.PNG, out);
	}

	// private BufferedImage createBufferedImage(int width, int height) {
	// BufferedImage a = (BufferedImage) table.createImage(1000, 100);
	// return a;
	// }

	public static BufferedImage createImage(JTable table) {
		JTableHeader tableHeaderComp = table.getTableHeader();
		int totalWidth = tableHeaderComp.getWidth();
		int totalHeight = tableHeaderComp.getHeight() + table.getHeight();
		BufferedImage tableImage = new BufferedImage(totalWidth, totalHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = (Graphics2D) tableImage.getGraphics();
		tableHeaderComp.paint(g2D);
		g2D.translate(0, tableHeaderComp.getHeight());
		table.paint(g2D);
		return tableImage;
	}

	public class MyTableModel extends AbstractTableModel {

		public String[] columnNamesTemp;
		private static final long serialVersionUID = 1L;
		private String[] columnNames = { "ویژگی", "مقدار" };
		private ArrayList<Object[]> data;

		public MyTableModel(ArrayList<Object[]> data) {
			this.data = data;
			columnNamesTemp = new String[columnNames.length];
			columnNamesTemp = columnNames.clone();
		}

		private Class<?>[] columnClasses = { String.class, String.class };

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}

		@Override
		public Class<?> getColumnClass(int col) {
			return columnClasses[col];
		}

		public void deleteRow(int row) {
			// Fire table row deletion notification to table.
			data.remove(row);
			fireTableRowsDeleted(row, row);
		}

		public void clearTable() {
			// Fire table row deletion notification to table.
			if (this.getRowCount() != 0) {
				data.clear();

				fireTableRowsDeleted(0, 0);
			}
		}

		public void addRow() {
			Object[] sample = { "انتخاب", "رديف" };
			data.add(sample);
			this.fireTableDataChanged();

		}

		public void addRow(Object[] rowData) {
			data.add(rowData);
			this.fireTableDataChanged();

		}

		@Override
		public Object getValueAt(int row, int col) {
			return data.get(row)[col];
		}

		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */

		/*
		 * Don't need to implement this method unless your table's editable.
		 */
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}

		/*
		 * Don't need to implement this method unless your table's data can
		 * change.
		 */
		@Override
		public void setValueAt(Object value, int row, int col) {
			// if (DEBUG) {
			System.out.println("Setting value at " + row + "," + col + " to "
					+ value + " (an instance of " + value.getClass() + ")");
			// }

			data.get(row)[col] = value;
			fireTableCellUpdated(row, col);

			// if (DEBUG) {
			System.out.println("New value of data:");
			// printAllTableData();
			// }
		}

		@SuppressWarnings("unused")
		private void printAllTableData() {
			int numRows = getRowCount();
			int numCols = getColumnCount();

			for (int i = 0; i < numRows; i++) {
				System.out.print("    row " + i + ":");
				for (int j = 0; j < numCols; j++) {
					System.out.print("  " + data.get(i)[j]);
				}
				System.out.println();
			}
			System.out.println("--------------------------");
		}
	}

	private static class HeaderRenderer implements TableCellRenderer {

		DefaultTableCellRenderer renderer;
		int horAlignment;

		public HeaderRenderer(JTable table, int horizontalAlignment) {
			horAlignment = horizontalAlignment;
			renderer = (DefaultTableCellRenderer) table.getTableHeader()
					.getDefaultRenderer();
		}

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int col) {
			Component c = renderer.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, col);
			JLabel label = (JLabel) c;
			label.setHorizontalAlignment(horAlignment);
			return label;
		}
	}

}
