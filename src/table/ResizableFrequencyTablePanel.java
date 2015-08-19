package table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * TableDemo is just like SimpleTableDemo, except that it uses a custom
 * TableModel.
 */
public class ResizableFrequencyTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	// private boolean DEBUG = false;
	public MyTableModel tableModel;
	public JTable table;
	public ArrayList<Object[]> data;
	int[] alignments = new int[] { JLabel.LEFT, JLabel.RIGHT, JLabel.RIGHT };

	public ResizableFrequencyTablePanel(String[] datas, String[] values) {

		super(new GridLayout(1, 0));
		data = new ArrayList<>();
		data = makeData(datas, values);
		tableModel = new MyTableModel(data);
		table = new JTable(tableModel);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setMaxWidth(100);
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
		table.setDefaultRenderer(Object.class, new MyTableCellRenderer2());

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
		scrollPane.setPreferredSize(new Dimension(7000, 20));
		// scrollPane.setHorizontalScrollBar(new
		// JScrollBar(JScrollBar.HORIZONTAL));
		// Add the scroll pane to this panel.
		add(scrollPane);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setLayout(new BorderLayout());
		f.setSize(700, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String[] a = { "a", "b", "c" };
		String[] b = { "a1", "b1", "c1" };

		ResizableFrequencyTablePanel ass = new ResizableFrequencyTablePanel(a,
				b);
		ass.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		f.add(ass, BorderLayout.CENTER);

		f.setVisible(true);

	}

	public void addRow() {
		tableModel.addRow();

	}

	private ArrayList<Object[]> makeData(String[] datas, String[] values) {
		ArrayList<Object[]> a = new ArrayList<Object[]>();
		for (int i = 0; i < datas.length; i++) {
			Object[] temp = new Object[2];
			temp[0] = datas[i];
			temp[1] = values[i];
			a.add(temp);
		}
		return a;

	}

	public class MyTableModel extends AbstractTableModel {
		public String[] columnNamesTemp;
		private static final long serialVersionUID = 1L;
		private String[] columnNames = { "داده", "فراوانی" };
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
			Object[] sample = { false, table.getRowCount() + 1, "شماره فرم",
					"نام سالن", "نام خط", "شماره (كد) ايستگاه ",
					"عنوان فرايند(ايستگاه) ", "وظیفه کاری", "نام اپراتور",
					"كد (شماره) فيلم", "تاريخ ارزيابي",
					"كد اپراتور (چپ يا راست بودن )",
					"Kg" + "وزن ابزار يا قطعه مورد استفاده ",
					"نرخ توليد  در ساعت", "A", "BS", "BM", "C", "D", "E", "F",
					"G", "H", "J", "K", "L", "M", "M", "P", "Q", "امتياز كمر",
					"LEVEL", "امتياز شانه", "LEVEL", "امتياز مچ", "LEVEL",
					"امتياز گردن", "LEVEL", "Driving", "LEVEL", "Vibration",
					"LEVEL", "Work Pace", "LEVEL", "Stress", "LEVEL",
					"امتياز كلي", "QEC LEVEL", "حمل بار", "توضيحات / ملاحضات",
					"نحوه گردش شغلي", "نوع پيشنهادات /  اقدام اصلاحي",
					"شماره اقدام اصلاحي	", "تاريخ اجراي اقدام اصلاحي",
					"امتياز كمر", "LEVEL", "امتياز شانه", "LEVEL", "امتياز مچ",
					"LEVEL", "امتياز گردن", "LEVEL", "امتياز كلي", "QEC LEVEL",
					"تاريخ ارزيابي مجدد", "نام كارشناس ارزياب", false };
			data.add(sample);
			this.fireTableDataChanged();

		}

		public void addRow(Object[] rowData) {
			if (rowData[48] != null) {
				if (rowData[48].toString().equals("1")) {
					rowData[48] = new Boolean(true);
				} else {
					rowData[48] = new Boolean(false);
				}
			}

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
			if ((row >= 0 && col <= getColumnCount() - 2) && col != 1) {
				if (!columnNames[col].contains("LEVEL"))
					return true;
			}
			return false;
		}

		/*
		 * Don't need to implement this method unless your table's data can
		 * change.
		 */
		@Override
		public void setValueAt(Object value, int row, int col) {
			// if (DEBUG) {
			// System.out.println("Setting value at " + row + "," + col
			// + " to " + value + " (an instance of "
			// + value.getClass() + ")");
			// }

			data.get(row)[col] = value;
			fireTableCellUpdated(row, col);

			// if (DEBUG) {
			// System.out.println("New value of data:");
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
