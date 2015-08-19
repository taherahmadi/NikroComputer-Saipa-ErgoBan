package graphicUI;

import graphicUI.chart.DataMaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;

import javax.swing.JDialog;

import logic.Logic;
import logic.Record;
import table.DataHandler;
import table.ResizableTablePanel;
import table.TablePanel;

public class BaseManager extends JFrame implements DataHandler {

	private static final long serialVersionUID = -4959259056573116762L;
	private Logic logic;
	private Font reportChartHeadersDefaultFont;
	private Font reportChartDomainsDefaultFont;
	private Font font;
	private JPanel header, mainPanel, rightPanel;
	private JComboBox<String> filterCombo;
	private JTextField filterField;
	private JButton cancelFilterButton;
	private JButton applyFilterButton;
	private JButton undoDelete;
	private JButton undoEdit;
	private JButton select;
	private ResizableTablePanel recordsPanel;
	private TablePanel tablePanel;
	private RecordPanel recordPanel;
	private JTabbedPane tabs;
	private JFileChooser fileChooser;
	private Vector<Record> editedRecords;
	private boolean allSelected;
	private boolean undoEdits;
	private boolean deleting;
	private int deleted;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(BaseManager.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (ParseException ex) {
			Logger.getLogger(BaseManager.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		BaseManager r = new BaseManager();

		try {
			File f = new File(args[0]);
			if (f.isFile())
				r.logic.loadFromFile(f);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Start without file!");
		}
		r.action(RightPanelLabel.CMND_LOAD);
	}

	public BaseManager() {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT,
					new File("./Files/B Yekan.ttf")).deriveFont(15f);
			reportChartHeadersDefaultFont = Font.createFont(Font.TRUETYPE_FONT,
					new File("./Files/B Titr Bold.ttf")).deriveFont(24f);
			reportChartDomainsDefaultFont = Font.createFont(Font.TRUETYPE_FONT,
					new File("./Files/B Nazanin.ttf")).deriveFont(15f);
		} catch (FontFormatException e1) {
			System.err.println("Font could not be created.");
		} catch (IOException e1) {
			System.err.println("Font could not be opened.");
		}
		deleted = 0;
		deleting = false;
		allSelected = false;
		undoEdits = false;
		logic = new Logic();
		DataMaker.setLogic(logic);
		editedRecords = new Vector<Record>();
		init();
	}

	private void init() {
		Dimension ss = getToolkit().getScreenSize();
		setIconImage((new ImageIcon("./Files/Images/ErgoBanLogo.png"))
				.getImage());
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(ss.width / 10, ss.height / 10);
		setPreferredSize(new Dimension(4 * ss.width / 5, 4 * ss.height / 5));

		headerMaker();
		rightPanelMaker();
		mainPanelMaker();

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0;
		gbc.weightx = 1;
		gbc.gridwidth = 2;
		gbc.ipady = 75;
		gbc.fill = GridBagConstraints.BOTH;
		getContentPane().add(header, gbc);

		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		getContentPane().add(mainPanel, gbc);

		gbc.gridx = 1;
		gbc.weightx = 0;
		gbc.ipadx = 10;
		getContentPane().add(rightPanel, gbc);

		this.pack();
		setVisible(true);
	}

	@Override
	protected void processWindowEvent(WindowEvent arg0) {
		if (arg0.getID() == WindowEvent.WINDOW_CLOSING
				&& thereIsUnsavedRecord()) {
			int result = JOptionPane.showConfirmDialog(this,
					"قبل از خروج ذخیره شود؟", "اخطار",
					JOptionPane.YES_NO_CANCEL_OPTION);
			switch (result) {
			case JOptionPane.YES_OPTION:
				action(RightPanelLabel.CMND_SAVE);
				System.exit(0);
				break;
			case JOptionPane.NO_OPTION:
				System.exit(0);
			case JOptionPane.CANCEL_OPTION:
			}
		} else {
			super.processWindowEvent(arg0);
		}
	}

	private void headerMaker() {
		GridBagConstraints miniGBC = new GridBagConstraints();
		header = new JPanel();
		header.setLayout(new GridBagLayout());
		header.setBackground(new Color(255, 255, 255));
		header.setPreferredSize(new Dimension(BaseManager.this
				.getPreferredSize().width, (int) (BaseManager.this
				.getPreferredSize().width * 0.0344)));

		JLabel saipaLogo = new JLabel() {

			private static final long serialVersionUID = 4233868805765740450L;

			@Override
			public void paint(Graphics arg0) {
				super.paint(arg0);
				arg0.drawImage((new ImageIcon("./Files/Images/SaipaLogo.jpg"))
						.getImage(), (getWidth() - 81) / 2, 0, 81, 75, null);
			}
		};
		miniGBC.gridx = 0;
		miniGBC.gridy = 0;
		miniGBC.weightx = 0;
		miniGBC.weighty = 1;
		miniGBC.ipadx = 81;
		miniGBC.fill = GridBagConstraints.BOTH;
		header.add(saipaLogo, miniGBC);

		JLabel middle = new JLabel() {

			private static final long serialVersionUID = 6274254607345675076L;

			@Override
			public void paint(Graphics arg0) {
				super.paint(arg0);
				arg0.drawImage(
						(new ImageIcon("./Files/Images/HeaderMiddle.jpg"))
								.getImage(), (getWidth() - 270) / 2, 12, 270,
						50, null);
			}

		};
		miniGBC.gridx = 1;
		miniGBC.weightx = 1;
		miniGBC.ipadx = 270;
		header.add(middle, miniGBC);

		JLabel ergoBanLogo = new JLabel() {

			private static final long serialVersionUID = -4205271899839375444L;

			@Override
			public void paint(Graphics arg0) {
				super.paint(arg0);
				arg0.drawImage(
						(new ImageIcon("./Files/Images/ErgoBanLogo.png"))
								.getImage(), (getWidth() - 65) / 2, 4, 65, 65,
						null);
			}

		};
		miniGBC.gridx = 2;
		miniGBC.weightx = 0;
		miniGBC.ipadx = 55 + 30;
		header.add(ergoBanLogo, miniGBC);
	}

	private void mainPanelMaker() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());

		GridBagConstraints miniGBC = new GridBagConstraints();
		tabs = new JTabbedPane();
		tabs.setBackground(new Color(230, 230, 230));
		tabs.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		recordsPanel = new ResizableTablePanel();
		tablePanel = recordsPanel.tablePanel;
		tablePanel.setDataHandler(this);
		tabs.add(recordsPanel, BorderLayout.CENTER);
		tabs.setTitleAt(0, "میز کار");

		recordPanel = new RecordPanel(this, font);
		JScrollPane recordScroolPane = new JScrollPane(recordPanel);
		recordScroolPane.getVerticalScrollBar().setUnitIncrement(20);

		tabs.add(recordScroolPane, BorderLayout.CENTER);
		tabs.setTitleAt(1, "رکورد جدید");

		miniGBC.gridx = 0;
		miniGBC.gridy = 0;
		miniGBC.weightx = 1;
		miniGBC.weighty = 1;
		miniGBC.fill = GridBagConstraints.BOTH;
		mainPanel.add(tabs, miniGBC);

		JLabel background = new JLabel() {

			private static final long serialVersionUID = -357510167874819866L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				g.drawImage(
						(new ImageIcon("./Files/Images/Back.gif")).getImage(),
						0, 0, getWidth(), getHeight(), null);
			}
		};
		mainPanel.add(background, miniGBC);
	}

	private void rightPanelMaker() {
		rightPanel = new JPanel();
		rightPanel.setLayout(new GridBagLayout());
		rightPanel.setBackground(Color.WHITE);
		GridBagConstraints miniGBC = new GridBagConstraints();

		miniGBC.gridwidth = 2;
		miniGBC.gridx = 0;
		miniGBC.gridy = 0;
		miniGBC.weightx = 1;
		miniGBC.weighty = 0.05;
		miniGBC.fill = GridBagConstraints.HORIZONTAL;
		rightPanel.add(new JLabel(), miniGBC);

		String[] addressTemplate = { "./Files/Images/RightIcons/",
				"./Files/Images/RightIcons/" };
		String[] address = new String[2];
		address[0] = addressTemplate[0] + "AddOff.png";
		address[1] = addressTemplate[1] + "AddOn.png";
		RightPanelLabel newRecord = new RightPanelLabel(
				"           رکورد جدید", RightPanelLabel.CMND_NEW_RECORD,
				address, RightPanelLabel.START, this, font);
		miniGBC.gridy = 1;
		miniGBC.weighty = 0;
		rightPanel.add(newRecord, miniGBC);

		address[0] = addressTemplate[0] + "RemoveOff.png";
		address[1] = addressTemplate[1] + "RemoveOn.png";
		RightPanelLabel deleteRecord = new RightPanelLabel(
				"           حذف رکورد", RightPanelLabel.CMND_DELETE_RECORD,
				address, RightPanelLabel.MIDDLE, this, font);
		miniGBC.gridy = 2;
		rightPanel.add(deleteRecord, miniGBC);

		address[0] = addressTemplate[0] + "SummaryOff.png";
		address[1] = addressTemplate[1] + "SummaryOn.png";
		RightPanelLabel summary = new RightPanelLabel("           گزارش گیری",
				RightPanelLabel.CMND_REPORT, address, RightPanelLabel.MIDDLE,
				this, font);
		miniGBC.gridy = 3;
		rightPanel.add(summary, miniGBC);

		// address[0] = addressTemplate[0] + "LoadOff.png";
		// address[1] = addressTemplate[1] + "LoadOn.png";
		// RightPanelLabel load = new RightPanelLabel("          " + " بارگذاری"
		// + " از آخرین تغییرات", RightPanelLabel.CMND_LOAD, address,
		// RightPanelLabel.MIDDLE, this, font);
		// miniGBC.gridx = 0;
		// miniGBC.gridy = 4;
		// rightPanel.add(load, miniGBC);

		address[0] = addressTemplate[0] + "LoadFromFileOff.gif";
		address[1] = addressTemplate[1] + "LoadFromFileOn.gif";
		RightPanelLabel loadFromFile = new RightPanelLabel(
				"           بارگذاری از فایل",
				RightPanelLabel.CMND_LOAD_FROM_FILE, address,
				RightPanelLabel.MIDDLE, this, font);
		miniGBC.gridy = 5;
		rightPanel.add(loadFromFile, miniGBC);

		address[0] = addressTemplate[0] + "SaveOff.gif";
		address[1] = addressTemplate[1] + "SaveOn.gif";
		RightPanelLabel save = new RightPanelLabel(
				"           ذخیره" // "           ذخیره ی تمام رکوردها"
				, RightPanelLabel.CMND_SAVE_ALL_RECORDS, address,
				RightPanelLabel.END, this, font);
		miniGBC.gridy = 6;
		rightPanel.add(save, miniGBC);

//		address[0] = addressTemplate[0] + "SaveOnFileOff.gif";
//		address[1] = addressTemplate[1] + "SaveOnFileOn.gif";
//		RightPanelLabel saveOnFile = new RightPanelLabel(
//				"           ذخیره ی رکوردهای انتخاب شده",
//				RightPanelLabel.CMND_SAVE_SELECTED_RECORDS, address,
//				RightPanelLabel.END, this, font);
//		miniGBC.gridy = 7;
		// rightPanel.add(saveOnFile, miniGBC);

		miniGBC.gridy = 8;
		miniGBC.weighty = 0.05;
		rightPanel.add(new JLabel(), miniGBC);

		select = new JButton("انتخاب همه");
		select.setFont(font);
		select.setForeground(Color.DARK_GRAY);
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectAll();
			}
		});
		miniGBC.gridy = 9;
		miniGBC.weighty = 0;
		rightPanel.add(select, miniGBC);

		miniGBC.gridy = 10;
		miniGBC.weighty = 0.05;
		rightPanel.add(new JLabel(), miniGBC);

		undoEdit = new JButton("بازگرداندن آخرین ویرایش");
		undoEdit.setForeground(Color.DARK_GRAY);
		undoEdit.setFont(font);
		undoEdit.setEnabled(false);
		undoEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// UNDO: START
				undoEdits = true;
				if (thereIsUnsavedRecord())
					action(RightPanelLabel.CMND_SAVE);
				Record record = logic.getByID(editedRecords.lastElement()
						.getId());
				if (record == null) { // There is no data with that ID
					// The record should be added.
					Record[] recs = new Record[1];
					recs[0] = editedRecords.lastElement();
					if (logic.removeFromDeleted(recs[0])) {
						// Number of deleted groups has been decreased.
						deleted--;
						if (deleted == 0)
							undoDelete.setEnabled(false);
					}
					logic.insert(recs);
				} else
					// The record should be updated.
					logic.update(editedRecords.lastElement());
				action(RightPanelLabel.CMND_LOAD);
				// UNDO: END
				// FINALIZE: START
				editedRecords.removeElementAt(editedRecords.size() - 1);
				if (editedRecords.isEmpty())
					undoEdit.setEnabled(false);
				undoEdits = false;
				// FINALIZE: END
			}
		});
		miniGBC.gridy = 11;
		miniGBC.weighty = 0;
		rightPanel.add(undoEdit, miniGBC);

		undoDelete = new JButton("بازگرداندن آخرین حذفیات");
		undoDelete.setForeground(Color.DARK_GRAY);
		undoDelete.setFont(font);
		undoDelete.setEnabled(false);
		undoDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// UNDO: START
				if (deleted > 0) {
					Loading3 l = new Loading3(BaseManager.this);
					new Thread(l).start();
					logic.undoDelete();
					action(RightPanelLabel.CMND_LOAD);
					deleted--;
					l.stop();
				}
				// UNDO: END
				// FINALIZE: START
				if (deleted == 0)
					undoDelete.setEnabled(false);
				// FINALIZE: END
			}
		});
		miniGBC.gridy = 12;
		rightPanel.add(undoDelete, miniGBC);

		miniGBC.gridy = 13;
		miniGBC.weighty = 0.05;
		rightPanel.add(new JLabel(), miniGBC);

		filterCombo = new JComboBox<String>();
		filterCombo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		filterCombo.setForeground(Color.DARK_GRAY);
		filterCombo.setFont(font);
		filterCombo.addItem("شماره فرم");
		filterCombo.addItem("نام سالن");
		filterCombo.addItem("نام خط");
		filterCombo.addItem("تاریخ ارزیابی");
		filterCombo.addItem("تاریخ ارزیابی مجدد");
		miniGBC.gridy = 14;
		miniGBC.gridwidth = 1;
		miniGBC.weightx = 0.3;
		miniGBC.weighty = 0;
		rightPanel.add(filterCombo, miniGBC);

		filterField = new JTextField();
		filterField.setForeground(Color.DARK_GRAY);
		filterField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		filterField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyChar() == '\n') {
					applyFilterButton.doClick();
				}
				super.keyPressed(arg0);
			}
		});
		miniGBC.gridx = 1;
		miniGBC.weightx = 0.7;
		miniGBC.ipadx = 30;
		rightPanel.add(filterField, miniGBC);

		applyFilterButton = new JButton("اعمال فیلتر");
		applyFilterButton.setFont(font);
		applyFilterButton.setForeground(Color.DARK_GRAY);
		applyFilterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleting = true;
				int[] columnGuide = { 1, 2, 3, 9, 64 };
				if (filterField.getText().length() > 0) {
					Record[] result;
					result = logic.searchByNumber(
							columnGuide[filterCombo.getSelectedIndex()],
							filterField.getText());
					tablePanel.tableModel.clearTable();
					addReordsToTable(result);
				}
				deleting = false;
			}
		});
		miniGBC.gridy = 15;
		miniGBC.gridx = 0;
		miniGBC.weightx = 0.3;
		miniGBC.ipadx = 0;
		rightPanel.add(applyFilterButton, miniGBC);

		cancelFilterButton = new JButton("لغو فیلتر");
		cancelFilterButton.setFont(font);
		cancelFilterButton.setForeground(Color.DARK_GRAY);
		cancelFilterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				action(RightPanelLabel.CMND_LOAD);
			}
		});
		miniGBC.gridy = 15;
		miniGBC.gridx = 1;
		miniGBC.weightx = 0.7;
		miniGBC.ipadx = 30;
		rightPanel.add(cancelFilterButton, miniGBC);

		miniGBC.gridx = 0;
		miniGBC.gridy = 16;
		miniGBC.weightx = 1;
		miniGBC.weighty = 0.7;
		miniGBC.gridwidth = 2;
		miniGBC.ipadx = 0;
		rightPanel.add(new JLabel(), miniGBC);

		JLabel projectLaborer = new JLabel() {

			private static final long serialVersionUID = 4367012359719043676L;

			@Override
			public void paint(Graphics arg0) {
				super.paint(arg0);
				arg0.drawImage(
						(new ImageIcon("./Files/Images/project.png").getImage()),
						0, 0, 239, 147, null);
			}

		};
		miniGBC.gridy = 17;
		miniGBC.weighty = 0;
		miniGBC.ipady = 150;
		miniGBC.fill = GridBagConstraints.BOTH;
		rightPanel.add(projectLaborer, miniGBC);

		JLabel designedAndDeveloped = new JLabel(
				"Designed and Developed by Nikro Computer", JLabel.CENTER);
		designedAndDeveloped.setFont(new Font("Arial", Font.ITALIC, 11));
		miniGBC.gridy = 18;
		miniGBC.ipady = 0;
		rightPanel.add(designedAndDeveloped, miniGBC);

		JLabel site = new JLabel("www.NikroComputer.com", JLabel.CENTER);
		site.setFont(new Font("Arial", Font.BOLD, 8));
		miniGBC.gridy = 19;
		rightPanel.add(site, miniGBC);

		miniGBC.gridy = 20;
		miniGBC.weighty = 0.1;
		rightPanel.add(new JLabel(), miniGBC);

		JLabel background = new JLabel() {

			private static final long serialVersionUID = -357510167874819866L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				g.drawImage(
						(new ImageIcon("./Files/Images/Back.gif")).getImage(),
						0, 0, getWidth(), getHeight(), null);
			}
		};
		miniGBC.gridx = 0;
		miniGBC.gridy = 0;
		miniGBC.weightx = 1;
		miniGBC.weighty = 1;
		miniGBC.gridwidth = 2;
		miniGBC.gridheight = 21;
		miniGBC.fill = GridBagConstraints.BOTH;
		rightPanel.add(background, miniGBC);
	}

	public void selectAll() {
		if (!allSelected) {
			for (int i = 0; i < tablePanel.table.getRowCount(); i++) {
				tablePanel.table.setValueAt(true, i, 0);
				if (!tablePanel.getSelectedRows().contains((Integer) i)) {
					tablePanel.getSelectedRows().add(i);
				}
			}
			allSelected = true;
			select.setText("عدم انتخاب همه");
		} else {

			for (int i = 0; i < tablePanel.table.getRowCount(); i++) {
				tablePanel.table.setValueAt(false, i, 0);
				tablePanel.getSelectedRows().remove((Integer) i);
			}
			allSelected = false;
			select.setText("انتخاب همه");
		}
		tablePanel.table.repaint();
	}

	private Record rowToRecord(int row) {
		Object[] rowData = new Object[tablePanel.tableModel.getColumnCount()];
		ArrayList<String> rowSrtings = new ArrayList<String>();
		rowData = tablePanel.data.get(row);
		for (int i = 1; i < tablePanel.tableModel.getColumnCount() - 1; i++) {
			if (i == 49) {
				if (rowData[i] != null) {
					if (rowData[i].toString().equals("true")) {
						rowSrtings.add("1");
					} else {
						rowSrtings.add("0");
					}
				}
			} else if (rowData[i] == null) {
				rowSrtings.add("");
			} else {
				rowSrtings.add(rowData[i].toString());
			}
		}
		String[] res = new String[rowSrtings.size()];
		res = rowSrtings.toArray(res);
		Record t = new Record(res);
		return t;
	}

	public void addReordsToTable(Record[] records) {
		// Taher
		deleting = true;
		// int rowC = tablePanel.tableModel.getRowCount();
		for (Record e : records) {
			Object[] r = new Object[tablePanel.table.getColumnCount()];
			r[0] = false;
			// r[1] = (++rowC);
			String[] ee = e.toStringArray();
			for (int y = 1; y < tablePanel.table.getColumnCount() - 1; y++) {
				// if
				// (tablePanel.tableModel.columnNamesTemp[y].contains("LEVEL"))
				// {
				// if (ee[y - 3] != null && !"".equals(ee[y - 3])) {
				// r[y] = findValue(ee, y - 3);
				//
				// } else {
				// r[y] = null;
				// }
				// } else {
				r[y] = ee[y - 1];
				// }
			}
			r[tablePanel.table.getColumnCount() - 1] = true;
			tablePanel.tableModel.addRow(r);
		}
		deleting = false;
	}

	public void action(int cmnd) {
		deleting = true;
		switch (cmnd) {
		case RightPanelLabel.CMND_SWITCH:
			tabs.setSelectedIndex(0);
			break;
		case RightPanelLabel.CMND_NEW_RECORD:
			tabs.setSelectedIndex(1);
			break;
		case RightPanelLabel.CMND_DELETE_RECORD:
			if (thereIsUnsavedRecord()) {
				JOptionPane.showMessageDialog(this,
						"ابتدا ذخیره کنید سپس دوباره تلاش کنید.", "خطا",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (tablePanel.getSelectedRows().size() > 0) {
					int result = JOptionPane.showConfirmDialog(this, "مایلید"
							+ " " + tablePanel.getSelectedRows().size()
							+ " رکورد حذف شود؟", "اخطار",
							JOptionPane.YES_NO_OPTION);
					switch (result) {
					case JOptionPane.YES_OPTION:
						Loading3 l = new Loading3(this);
						new Thread(l).start();
						delete();
						l.stop();
						action(RightPanelLabel.CMND_LOAD);
						break;
					case JOptionPane.NO_OPTION:
						break;
					}
				}
			}
			break;
		case RightPanelLabel.CMND_REPORT:
			tabs.addTab("گزارش گیری", new ReportPanelV2(this, font,
					reportChartHeadersDefaultFont,
					reportChartDomainsDefaultFont, new Color(230, 230, 230)));
			tabs.setSelectedIndex(tabs.getTabCount() - 1);
			break;
		case RightPanelLabel.CMND_SAVE:
			ArrayList<Record> buffer = new ArrayList<>();
			for (int i = 0; i < tablePanel.tableModel.getRowCount(); i++) {
				if ((boolean) tablePanel.tableModel.getValueAt(i,
						tablePanel.table.getColumnCount() - 1) == false) {
					buffer.add(rowToRecord(i));
				}
			}
			Record[] u = new Record[buffer.size()];
			u = buffer.toArray(u);

			Record[] unsaved = logic.insert(u);
			for (int i = 0; i < tablePanel.tableModel.getRowCount(); i++)
				if (isNotInUnsaved(unsaved,
						Integer.parseInt((String) tablePanel.tableModel
								.getValueAt(i, 1))))
					tablePanel.tableModel.setValueAt(true, i,
							tablePanel.table.getColumnCount() - 1);
			if (unsaved.length == 0) {
				if (!undoEdits)
					JOptionPane.showMessageDialog(this,
							"اطلاعات با موفقیت ذخیره شد.", "اتمام ذخیره",
							JOptionPane.INFORMATION_MESSAGE);
			} else {
				int answer = JOptionPane
						.showConfirmDialog(
								this,
								"تعدادی از رکوردها اضافه نشدند."
										+ "\n"
										+ "آیا مایل به مشاهده ی رکوردهای اضافه نشده هستید؟",
								"اخطار", JOptionPane.YES_NO_OPTION,
								JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION)
					showFailedRecords(unsaved);
			}
			break;
		case RightPanelLabel.CMND_SAVE_ALL_RECORDS:
		case RightPanelLabel.CMND_SAVE_SELECTED_RECORDS:
			Record[] records = null;
			switch (cmnd) {
			case RightPanelLabel.CMND_SAVE_ALL_RECORDS:
				records = new Record[tablePanel.table.getRowCount()];
				for (int i = 0; i < tablePanel.table.getRowCount(); i++)
					records[i] = rowToRecord(i);
				break;
			case RightPanelLabel.CMND_SAVE_SELECTED_RECORDS:
				records = new Record[tablePanel.getSelectedRows().size()];
				for (int i = 0; i < tablePanel.getSelectedRows().size(); i++)
					records[i] = rowToRecord(tablePanel.getSelectedRows()
							.get(i));
				break;
			}
			saveRecords(records);
			break;
		case RightPanelLabel.CMND_LOAD:
			if (!undoEdits && thereIsUnsavedRecord()) {
				switch (JOptionPane
						.showConfirmDialog(
								null,
								"تعدادی از رکروردها ذخیره نشده اند."
										+ "\n"
										+ "بعد از بارگذاری حذف خواهند شد. آیا ذحیره می کنید؟",
								"خطر", JOptionPane.YES_NO_CANCEL_OPTION)) {
				case JOptionPane.YES_OPTION:
					action(RightPanelLabel.CMND_SAVE);
					break;
				case JOptionPane.NO_OPTION:
					break;
				case JOptionPane.CANCEL_OPTION:
					return;
				}
			}
			tablePanel.tableModel.clearTable();
			Record[] load = logic.report();
			try {
				if (load != null) {
					addReordsToTable(load);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case RightPanelLabel.CMND_LOAD_FROM_FILE:
			if (thereIsUnsavedRecord())
				action(RightPanelLabel.CMND_SAVE);
			fileChooser = new JFileChooser();
			FileFilter filter2 = new FileNameExtensionFilter(
					"DataBase (.csv, .dat, .xls)", "csv", "dat", "xls");
			fileChooser.addChoosableFileFilter(filter2);
			fileChooser.setFileFilter(filter2);
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int i = fileChooser.showDialog(this, "Open");
			if (i != JFileChooser.CANCEL_OPTION) {
				File file = fileChooser.getSelectedFile();
				if (!(file.getAbsoluteFile().toString().endsWith(".csv")
						|| file.getAbsoluteFile().toString().endsWith(".xls") || file
						.getAbsoluteFile().toString().endsWith(".dat"))) {
					JOptionPane.showMessageDialog(fileChooser,
							"نوع فایل صحیح نیست");
				} else if (file != null) {
					Loading3 l = new Loading3(this);
					new Thread(l).start();
					Record[] res = logic.loadFromFile(file);
					l.stop();

					if (res.length != 0) {
						int r = JOptionPane
								.showConfirmDialog(
										this,
										"تعدادی از رکوردها اضافه نشدند."
												+ "\n"
												+ "آیا مایل به مشاهده ی رکوردهای اضافه نشده هستید؟",
										"اخطار", JOptionPane.YES_NO_OPTION,
										JOptionPane.YES_NO_OPTION);
						if (r == JOptionPane.YES_OPTION)
							showFailedRecords(res);
					}
					action(RightPanelLabel.CMND_LOAD);
				}
			}
			break;
		case RightPanelLabel.CMND_CLOSE_TAB:
			tabs.remove(tabs.getSelectedIndex());
			break;
		}
		deleting = false;
	}

	private boolean isNotInUnsaved(Record[] unsaved, int valueAt) {
		for (int i = 0; i < unsaved.length; i++)
			if (unsaved[i].getId() == valueAt)
				return false;
		return true;
	}

	private void delete() {
		if (tablePanel.getSelectedRows().size() == 1) {
			try {
				if (tablePanel.table.getSelectedRow() != -1
						&& (boolean) tablePanel.tableModel.getValueAt(
								tablePanel.table.getSelectedRow(),
								tablePanel.tableModel.getColumnCount() - 1) == false) {
					tablePanel.tableModel.deleteRow(tablePanel.table
							.getSelectedRow());
				} else if (tablePanel.table.getSelectedRow() != -1
						&& (boolean) tablePanel.tableModel.getValueAt(
								tablePanel.table.getSelectedRow(),
								tablePanel.tableModel.getColumnCount() - 1) == true) {
					Record[] res = new Record[1];
					res[0] = rowToRecord(tablePanel.table.getSelectedRow());
					for (int i = 0; i < editedRecords.size(); i++) {
						if (res[0].getId() == editedRecords.elementAt(i)
								.getId()) {
							editedRecords.removeElementAt(i);
							i--;
						}
					}
					if (editedRecords.size() == 0)
						undoEdit.setEnabled(false);
					logic.delete(res);
					tablePanel.tableModel.deleteRow(tablePanel.table
							.getSelectedRow());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			tablePanel.getSelectedRows().clear();

		} else if (tablePanel.getSelectedRows().size() > 1) {
			ArrayList<Record> deletedRecs = new ArrayList<Record>();
			for (int i = 0; i < tablePanel.getSelectedRows().size(); i++) {
				try {
					if ((boolean) tablePanel.tableModel.getValueAt(tablePanel
							.getSelectedRows().get(i), tablePanel.tableModel
							.getColumnCount() - 1) == false) {
						tablePanel.tableModel.deleteRow(tablePanel
								.getSelectedRows().get(i));
					} else if ((boolean) tablePanel.tableModel.getValueAt(
							tablePanel.getSelectedRows().get(i),
							tablePanel.tableModel.getColumnCount() - 1) == true) {
						deletedRecs.add(rowToRecord(tablePanel
								.getSelectedRows().get(i)));

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			int[] tmp = new int[tablePanel.getSelectedRows().size()];
			for (int i = 0; i < tmp.length; i++) {
				tmp[i] = tablePanel.getSelectedRows().get(i);
			}
			Arrays.sort(tmp);
			tablePanel.getSelectedRows().clear();
			for (int i = 0; i < tmp.length; i++) {
				if (!tablePanel.getSelectedRows().contains(tmp[i])) {
					tablePanel.getSelectedRows().add(tmp[i]);
				}
			}
			int consSelectedRows = tablePanel.getSelectedRows().size();
			for (int i = consSelectedRows - 1; i >= 0; i--) {
				tablePanel.tableModel.deleteRow(tablePanel.getSelectedRows()
						.get(i));
			}
			Record[] res0 = new Record[deletedRecs.size()];
			res0 = deletedRecs.toArray(res0);
			logic.delete(res0);
			tablePanel.getSelectedRows().clear();
		}
		deleted++;
		undoDelete.setEnabled(true);
	}

	public Logic getLogic() {
		return logic;
	}

	private boolean thereIsUnsavedRecord() {
		for (int i = 0; i < tablePanel.table.getRowCount(); i++) {
			if (!(boolean) tablePanel.table.getValueAt(i,
					tablePanel.table.getColumnCount() - 1)) {
				return true;
			}
		}
		return false;
	}

	public void setDeleting(boolean deleting) {
		this.deleting = deleting;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getDeleted() {
		return deleted;
	}

	private void showFailedRecords(Record[] failedRecords) {
		Dimension ss = getToolkit().getScreenSize();
		JDialog failedRecordsDialog = new JDialog(this, "رکوردهای اضافه نشده");
		ResizableTablePanel failedRecordsPanel = new ResizableTablePanel();
		failedRecordsDialog.setSize(5 * ss.width / 9, 5 * ss.height / 9);
		failedRecordsDialog.setLocation(2 * ss.width / 9, 2 * ss.height / 9);
		failedRecordsDialog.setModal(true);
		failedRecordsDialog.add(failedRecordsPanel);
		failedRecordsPanel.tablePanel.setDataHandler(new DataHandler() {

			@Override
			public boolean isEditable(int row, int col) {
				return false;
			}

			@Override
			public boolean getDeleting() {
				return true;
			}

			@Override
			public void dataEdited() {
			}
		});

		// Add failed records to table: START
		for (Record e : failedRecords) {
			Object[] r = new Object[tablePanel.table.getColumnCount()];
			r[0] = false;
			String[] ee = e.toStringArray();
			for (int y = 1; y < tablePanel.table.getColumnCount() - 1; y++)
				r[y] = ee[y - 1];
			r[tablePanel.table.getColumnCount() - 1] = false;
			failedRecordsPanel.tablePanel.tableModel.addRow(r);
		}
		// Add failed records to table: END

		failedRecordsDialog.setVisible(true);
	}

	@Override
	public boolean getDeleting() {
		return deleting;
	}

	@Override
	public void dataEdited() {
		Record res = logic.getByID(Integer
				.parseInt((String) tablePanel.tableModel.getValueAt(
						tablePanel.table.getSelectedRow(), 1)));
		editedRecords.add(res);
		undoEdit.setEnabled(true);
		res = rowToRecord(tablePanel.table.getSelectedRow());
		logic.update(res);
	}

	@Override
	public boolean isEditable(int row, int col) {
		if ((row >= 0 && col <= tablePanel.table.getColumnCount() - 2)
				&& col != 1)
			return true;
		else
			return false;
	}

	public void saveRecords(Record[] records) {
		fileChooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Excel(.csv) ", "csv");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);

		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.showDialog(this, "Save");
		File dfile = fileChooser.getSelectedFile();

		if (dfile != null) {
			if (dfile.getName().endsWith("csv"))
				logic.csvoutput(dfile.getAbsolutePath(), records);
			else if (!dfile.getName().contains("."))
				logic.csvoutput(dfile.getAbsolutePath() + ".csv", records);
			else
				logic.csvoutput(
						dfile.getAbsolutePath().substring(0,
								dfile.getAbsolutePath().lastIndexOf("."))
								+ ".csv", records);
		}
	}

}

class RightPanelLabel extends JLabel implements MouseListener {

	private static final long serialVersionUID = -8111161047421268557L;
	public static final int CMND_NEW_RECORD = 0;
	public static final int CMND_DELETE_RECORD = 1;
	public static final int CMND_REPORT = 2;
	public static final int CMND_SAVE = 3;
	public static final int CMND_SAVE_ALL_RECORDS = 4;
	public static final int CMND_SAVE_SELECTED_RECORDS = 5;
	public static final int CMND_LOAD = 6;
	public static final int CMND_LOAD_FROM_FILE = 7;
	public static final int CMND_SWITCH = 8;
	public static final int CMND_CLOSE_TAB = 9;
	private int mode, imageIndex, command;
	private Image[] images;
	private boolean firstPaint;
	private BaseManager parent;

	public static final Image shadowLine = (new ImageIcon(
			"./Files/Images/ShadowLine.gif")).getImage();
	public static final Image line = (new ImageIcon("./Files/Images/Line.gif"))
			.getImage();
	public static final int START = 0;
	public static final int MIDDLE = 1;
	public static final int END = 2;

	public RightPanelLabel(String text, int cmnd, String[] imageAddresses,
			int mode, BaseManager parent, Font font) {
		super(text, RIGHT);
		this.mode = mode;
		this.command = cmnd;
		this.parent = parent;
		this.firstPaint = true;
		addMouseListener(this);
		loadImages(imageAddresses);
		this.setForeground(Color.DARK_GRAY);
		if (!text.contains("PDF")) {
			this.setFont(font.deriveFont(15f));
		} else {
			this.setFont(getFont().deriveFont(13f));
		}
	}

	private void loadImages(String[] imageAddresses) {
		images = new Image[imageAddresses.length];
		for (int i = 0; i < imageAddresses.length; i++) {
			images[i] = (new ImageIcon(imageAddresses[i])).getImage();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (firstPaint) {
			int width = getWidth();
			switch (mode) {
			case START:
				g.drawImage(line, width / 10, 0, (int) (7.75 * width / 10), 2,
						null);
				break;
			case MIDDLE:
			case END:
				g.drawImage(shadowLine, width / 10, 0,
						(int) (7.75 * width / 10), 1, null);
			}
			switch (mode) {
			case START:
			case MIDDLE:
				g.drawImage(shadowLine, width / 10, getHeight() - 1,
						(int) (7.75 * width / 10), 1, null);
				break;
			case END:
				g.drawImage(line, width / 10, getHeight() - 2,
						(int) (7.75 * width / 10), 2, null);
				break;
			}
		}
		g.drawImage(images[imageIndex], getWidth() - 30, -3, 23, 23, null);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		imageIndex = 1;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		imageIndex = 0;
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		parent.action(command);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
