/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

/**
 * @author Tahe
 */
class MyTableCellRenderer extends DefaultTableCellRenderer implements
		TableCellEditor {

	private static final long serialVersionUID = -1185427202341098999L;

	public MyTableCellRenderer() {
		super();
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component tableCellRendererComponent = super
				.getTableCellRendererComponent(table, value, isSelected,
						hasFocus, row, column);
		setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		if ((boolean) table.getValueAt(row, table.getColumnCount() - 1) == false) {
			// setForeground(Color.red);
			// setBackground(Color.red);
			if ((int) row % 2 == 0) {
				setBackground(new Color(244, 216, 205));
			} else {
				setBackground(new Color(241, 220, 205));
			}
		} else {
			// setBackground(Color.red);
			// setForeground(Color.red);
			if ((int) row % 2 == 0) {
				setBackground(new Color(235, 235, 235));
			} else {
				setBackground(new Color(252, 252, 252));
			}
		}
		if ((boolean) table.getValueAt(row, 0) == true) {
			setBackground(Color.lightGray);
		}

		if (table.getSelectedRow() == row) {
			setBackground(new Color(212, 239, 255));
		}

		setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		// setText(value !=null ? value.toString() : "");
		return tableCellRendererComponent;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		return this;
		 // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public Object getCellEditorValue() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public boolean stopCellEditing() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void cancelCellEditing() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

}
