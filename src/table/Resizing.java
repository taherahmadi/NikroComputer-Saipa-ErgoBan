package table;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class Resizing implements ChangeListener {

	JPanel panel;
	JPanel parentPanel;
	JTable table;
	JSlider slider;
	int panelH, panelW;
	int rowsHeight;
	int fontSize;

	public Resizing(JPanel panel) {
		this.panel = panel;
		panelH = panel.getPreferredSize().height;
		panelW = panel.getPreferredSize().width;
		fontSize = 13;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider slider = (JSlider) e.getSource();

		int value = slider.getValue();
		if (slider.getOrientation() == JSlider.HORIZONTAL) {
			Dimension d = new Dimension(panelW + 3 * value, panelH);
			table.setRowHeight(rowsHeight + value / 20);
			panel.setPreferredSize(d);
			table.setFont(table.getFont().deriveFont(
					(float) fontSize + value / 30));
		}

		panel.revalidate();
	}

	public JSlider setSlider(final JSlider slider) {
		this.slider = slider;
		slider.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '+') {
					slider.setValue(slider.getValue() + 10);
				} else if (e.getKeyChar() == '-') {
					slider.setValue(slider.getValue() - 10);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
		slider.addChangeListener(this);
		return slider;
	}

	public JSlider setSlider(final JSlider slider, JTable table) {
		this.slider = slider;
		this.table = table;
		this.rowsHeight = table.getRowHeight();
		slider.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '+') {
					slider.setValue(slider.getValue() + 10);
				} else if (e.getKeyChar() == '-') {
					slider.setValue(slider.getValue() - 10);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		slider.addChangeListener(this);
		return slider;
	}

}
