/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
public class TablePanel {

	private static final long serialVersionUID = 1L;
	// Saeid
	private DataHandler dataHandler;
	public MyTableModel tableModel;
	public JTable table;
	public ArrayList<Object[]> data;
	private ArrayList<Integer> selectedRows;
	int[] alignments = new int[] { JLabel.LEFT, JLabel.RIGHT, JLabel.RIGHT };
	JPanel parent ;
	GridBagConstraints c ;
	public TablePanel(JPanel parent,GridBagConstraints c) {
		this.parent=parent;
		this.c=c;
		dataHandler = null;
		data = new ArrayList<>();
		selectedRows = new ArrayList<>();
		tableModel = new MyTableModel(data);
		table = new JTable(tableModel);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setMaxWidth(60);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setMaxWidth(50);
		table.getColumnModel().getColumn(2).setMaxWidth(90);
		table.getColumnModel().getColumn(49).setMaxWidth(60);
		for (int i = 0; i < tableModel.getColumnCount(); i++) {
			if (i > 14 && i < 31) {
				table.getColumnModel().getColumn(i).setMaxWidth(70);
			} else if (tableModel.columnNamesTemp[i].contains("LEVEL")) {
				if (tableModel.columnNamesTemp[i].equals("QEC LEVEL")) {
					table.getColumnModel().getColumn(i).setMinWidth(90);
					table.getColumnModel().getColumn(i).setMaxWidth(200);
				} else {
					table.getColumnModel().getColumn(i).setMaxWidth(90);
				}
				if (tableModel.columnNamesTemp[i - 1].equals("Work Pace")) {
					table.getColumnModel().getColumn(i - 1).setMinWidth(90);
					table.getColumnModel().getColumn(i - 1).setMaxWidth(150);
				} else {
					table.getColumnModel().getColumn(i - 1).setMaxWidth(90);
				}
			} else {
				// table.getColumnModel().getColumn(i).setMinWidth(120);
				// table.getColumnModel().getColumn(i).setMaxWidth(250);
			}
		}
		table.getColumnModel().getColumn(table.getColumnCount() - 1)
				.setMaxWidth(60);
		table.getColumnModel().getColumn(table.getColumnCount() - 1)
				.setResizable(false);

		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (!dataHandler.getDeleting()
						&& (table.getSelectedRow() != -1)
						&& e.getColumn() != table.getColumnCount() - 1
						&& e.getColumn() != 1) {
					int row = table.getSelectedRow();
					int col = e.getColumn();

					if (col == 0
							&& (boolean) table.getValueAt(row, col) == true) {
						if (!selectedRows.contains((Integer) row))
							selectedRows.add(row);
					} else if (col == 0
							&& (boolean) table.getValueAt(row, col) == false) {
						selectedRows.remove((Object) row);
					} else if (col != 0
							&& (boolean) table.getValueAt(row,
									table.getColumnCount() - 1)) {
						dataHandler.dataEdited();
					}
				}
			}
		});

		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					private int count = 0;

					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						if (count > 1 && !arg0.getValueIsAdjusting()) {
							int[] draggedRows = table.getSelectedRows();
							for (int i = 0; i < draggedRows.length; i++)
								if (selectedRows
										.contains((Integer) draggedRows[i])) {
									selectedRows
											.remove((Integer) draggedRows[i]);
									table.setValueAt(false, draggedRows[i], 0);
								} else {
									selectedRows.add((Integer) draggedRows[i]);
									table.setValueAt(true, draggedRows[i], 0);
								}
							// Finalize
							count = 0;
						} else if (arg0.getValueIsAdjusting())
							count++;
						else
							count = 0;
					}
				});

		// {
		//
		// @Override
		// public Component prepareRenderer(TableCellRenderer renderer,
		// int row, int column) {
		// JLabel label = (JLabel) super.prepareRenderer(renderer, row, column);
		// if ((boolean) table.getValueAt(row, table.getColumnCount() - 1) ==
		// false) {
		// if ((int) row % 2 == 0) {
		// label.setBackground(new Color(244, 226,215));
		// } else {
		// label.setBackground(new Color(241, 220,205));
		// }
		// } else {
		// if ((int) row% 2 == 0) {
		// label.setBackground(new Color(255, 255, 255));
		// } else {
		// label.setBackground(new Color(252, 252, 252));
		// }
		//
		// }
		// if(table.getSelectedRow()==row)
		// label.setBackground(Color.LIGHT_GRAY);
		// return label;
		// }
		// };
		// table.prepareRenderer(null, WIDTH, WIDTH);
		// table.setRowSelectionAllowed(true);
		// table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setDefaultRenderer(Object.class, new MyTableCellRenderer());

		// {
		//
		// @Override
		// public Component getTableCellRendererComponent(JTable table, Object
		// value, boolean isSelected, boolean hasFocus, int row, int column) {
		// JLabel label = (JLabel) table.prepareRenderer(null, row, column);
		// if ((boolean) table.getValueAt(row, table.getColumnCount() - 1) ==
		// false) {
		// if ((int) row % 2 == 0) {
		// label.setBackground(new Color(244, 226,215));
		// } else {
		// label.setBackground(new Color(241, 220,205));
		// }
		// } else {
		// if ((int) row% 2 == 0) {
		// label.setBackground(new Color(255, 255, 255));
		// } else {
		// label.setBackground(new Color(252, 252, 252));
		// }
		//
		// }
		// if(table.getSelectedRow()==row)
		// label.setBackground(Color.LIGHT_GRAY);
		// return label;
		// }
		// });
		// {
		//
		// @Override
		// public Component getTableCellRendererComponent(JTable table, Object
		// value, boolean isSelected, boolean hasFocus, int row, int column) {
		// Component cell= table.getCellRenderer(row,
		// column).getTableCellRendererComponent(table, value, isSelected,
		// hasFocus, row, column);
		//
		//
		// for(int i=1;i<table.getRowCount();i++) {
		// if((boolean)table.getValueAt(i, table.getColumnCount()-1) ==false)
		// {
		// setForeground(Color.black);
		// setBackground(Color.red);
		// }
		// else
		// {
		// setBackground(Color.white);
		// setForeground(Color.black);
		// }
		// }
		// return cell;
		// }
		// });
		// DefaultTableCellRenderer rightRenderer = new
		// DefaultTableCellRenderer();
		// rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
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
		table.setRowHeight(25);
		// table.setPreferredScrollableViewportSize(new Dimension(10000, 200));

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
//		JViewport tableView = new JViewport();
//		tableView.add(table);
//		scrollPane.setViewport(tableView);
		parent.setSize(table.getWidth(),table.getHeight());
//		parent.setPreferredSize(new Dimension(70000,10000));
//		scrollPane.setVerticalScrollBar(new JScrollBar(JScrollBar.VERTICAL));
//		scrollPane.setHorizontalScrollBar(new JScrollBar(JScrollBar.HORIZONTAL));
		// Add the scroll pane to this panel.
//		parent.add(table.getTableHeader());
//		parent.add(table, c);
		// Add header in NORTH slot
		parent.add(table.getTableHeader(), BorderLayout.NORTH);

		// Add table itself to CENTER slot
		parent.add(table, BorderLayout.CENTER);
	}

	public String doSaveAs(File f) throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"PNG Image Files", "png");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		String filename = "";
		int option = fileChooser.showSaveDialog(this.parent);
		if (option == JFileChooser.APPROVE_OPTION) {
			filename = fileChooser.getSelectedFile().getPath();
			if (!filename.endsWith(".png")) {
				filename = filename + ".png";
			}
			saveTableAsPNG(new File(filename), this.table, parent.getWidth(),
					parent.getHeight());
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
		private String[] columnNames = {
				"انتخاب",
				"رديف",
				"شماره فرم",// 3
				"نام سالن",
				"نام خط",
				"شماره (كد) ايستگاه ",// 3
				"عنوان فرايند(ايستگاه) ", "وظیفه کاری", "نام اپراتور",
				"كد (شماره) فيلم", "تاريخ ارزيابي",
				"كد اپراتور (چپ يا راست بودن )", "قطعه یا ابزار مورد استفاده",
				"Kg" + "وزن ابزار يا قطعه مورد استفاده ", "نرخ توليد  در ساعت",
				"A", "BS", "BM", "C", "D", "E", "F", "G", "H", "J", "K", "L",
				"M", "N", "P", "Q", "امتياز كمر", "LEVEL", "امتياز شانه",
				"LEVEL", "امتياز مچ", "LEVEL", "امتياز گردن", "LEVEL",
				"Driving", "LEVEL", "Vibration", "LEVEL", "Work Pace", "LEVEL",
				"Stress", "LEVEL", "امتياز كلي", "QEC LEVEL", "حمل بار",
				"توضيحات / ملاحضات", "نحوه گردش شغلي",
				"نوع پيشنهادات /  اقدام اصلاحي", "شماره اقدام اصلاحي	",
				"تاريخ اجراي اقدام اصلاحي", "امتياز كمر", "LEVEL",
				"امتياز شانه", "LEVEL", "امتياز مچ", "LEVEL", "امتياز گردن",
				"LEVEL", "امتياز كلي", "QEC LEVEL", "تاريخ ارزيابي مجدد",
				"نام كارشناس ارزياب", "ذخیره شده" };
		private ArrayList<Object[]> data;

		public MyTableModel(ArrayList<Object[]> data) {
			this.data = data;
			columnNamesTemp = new String[columnNames.length];
			columnNamesTemp = columnNames.clone();
		}

		private Class<?>[] columnClasses = {
				Boolean.class,
				Integer.class,
				Integer.class,
				String.class,
				String.class,
				String.class,
				String.class,
				String.class,
				String.class,
				String.class,
				String.class,
				String.class,
				String.class,//
				Integer.class, // float.class,
				String.class, // Saeid
				Integer.class, String.class, String.class, Integer.class,
				Integer.class, Integer.class, Integer.class, Integer.class,
				Integer.class, Integer.class, Integer.class, Integer.class,
				Integer.class, Integer.class, Integer.class, Integer.class,
				Integer.class, String.class, Integer.class, String.class,
				Integer.class, String.class, Integer.class, String.class,
				Integer.class, String.class, Integer.class, String.class,
				Integer.class, String.class,
				Integer.class,
				String.class,
				Integer.class,
				String.class,
				Boolean.class, // hamle bar

				String.class, String.class, String.class, Integer.class,
				String.class, Integer.class, String.class, Integer.class,
				String.class, Integer.class, String.class, Integer.class,
				String.class, Integer.class, String.class, String.class,
				String.class, Boolean.class

		};

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			if(dataHandler == null)
				return 1;
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

		public void addRow(Object[] rowData) {
			if (rowData[49] != null) {
				if (rowData[49].toString().equals("1")) {
					rowData[49] = new Boolean(true);
				} else {
					rowData[49] = new Boolean(false);
				}
			}

			data.add(rowData);
			this.fireTableDataChanged();

		}

		@Override
		public Object getValueAt(int row, int col) {
			if(dataHandler == null)
				return "Data Handler is null";
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
			// Changed by Saeid
			return dataHandler.isEditable(row, col);
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

	// Saeid's Method
	public void setDataHandler(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}

	public ArrayList<Integer> getSelectedRows() {
		return selectedRows;
	}

}
