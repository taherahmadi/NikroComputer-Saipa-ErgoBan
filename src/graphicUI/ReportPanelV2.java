package graphicUI;

import graphicUI.chart.BarChartPanel;
import graphicUI.chart.DataMaker;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;

import logic.Record;
import say.swing.JFontChooser;
import table.DataHandler;
import table.FrequencyTablePanel;
import table.ResizableTablePanel;

public class ReportPanelV2 extends JPanel implements DataHandler {

	private static final long serialVersionUID = -1578526336893475350L;
	private BaseManager parent;
	private Font fontOfHeaders;
	private Font fontOfDomains;
	private Font font;
	private AdaptedRadioButton reassessment;
	private JComboBox<String> filterList;
	private JComboBox<String> filters;
	private JTabbedPane lp;
	private JScrollPane rp;
	private JButton headerFontSetter;
	private JButton domainFontSetter;
	private JButton apply;
	private JButton print;
	private JButton pdf;
	private JButton excel;
	private String[] info;
	private String[] infoNum;

	public ReportPanelV2(BaseManager parent, Font font,
			Font defaultFontOfHeaders, Font defaultFontOfDomains,
			Color background) {
		fontOfHeaders = defaultFontOfHeaders;
		fontOfDomains = defaultFontOfDomains;
		this.font = font;
		this.parent = parent;
		setBackground(background);
		setLayout(new GridBagLayout());
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lp = makeLPanel();
		lp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		rp = makeRPanel();
		rp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lp.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (lp.getTabCount() == 0)
					setExportButtons(false);
				else
					setExportButtons(true);
				try {
					if (lp.getComponentAt(lp.getSelectedIndex()) instanceof ResizableTablePanel)
						excel.setEnabled(true);
					else
						excel.setEnabled(false);
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					excel.setEnabled(false);
				}
			}

			private void setExportButtons(boolean enable) {
				print.setEnabled(enable);
				pdf.setEnabled(enable);
			}
		});

		showLP();
	}

	private void showLP() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 0;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.gridheight = 1;
		add(rp, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridheight = 1;
		add(lp, gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.ipadx = 270;
		add(new JLabel("aaa") {

			private static final long serialVersionUID = -1208676327717537510L;

			@Override
			public void paint(Graphics arg0) {
			}
		}, gbc);
	}

	private JScrollPane makeRPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		JScrollPane jsp = new JScrollPane(panel);
		jsp.getVerticalScrollBar().setUnitIncrement(20);
		GridBagConstraints gbc = new GridBagConstraints();
		panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		makeFilterList(panel, gbc);
		gbc.gridy++;
		gbc.weightx = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridy++;
		filters = new JComboBox<String>();
		filters.setFont(font);
		filters.addItem("فیلترها را بارگذازی کنید");
		filters.setEnabled(false);
		filters.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panel.add(filters, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		reassessment = new AdaptedRadioButton("ارزیابی مجدد",
				AdaptedRadioButton.REASSESSMENT, panel, font, gbc);
		reassessment.setEnabled(false);

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		headerFontSetter = new JButton("قلم تیتر نمودارها");
		headerFontSetter.setFont(font);
		headerFontSetter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFontChooserV2 jfc = new JFontChooserV2();
				jfc.setSelectedFont(fontOfHeaders);
				int result = jfc.showDialog(null);
				if (result == JFontChooser.OK_OPTION) {
					fontOfHeaders = jfc.getSelectedFont();
					for (int i = 0; i < lp.getTabCount(); i++)
						if (lp.getComponentAt(i) instanceof BarChartPanel) {
							BarChartPanel bcp = (BarChartPanel) lp
									.getComponentAt(i);
							bcp.getChart().getTitle().setFont(fontOfHeaders);
							bcp.repaint();
						}
				}
			}
		});
		// panel.add(headerFontSetter, gbc);

		gbc.gridy++;
		domainFontSetter = new JButton("قلم دامنه های نمودارها");
		domainFontSetter.setFont(font);
		domainFontSetter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFontChooserV2 jfc = new JFontChooserV2();
				jfc.setSelectedFont(fontOfDomains);
				int result = jfc.showDialog(null);
				if (result == JFontChooser.OK_OPTION) {
					fontOfDomains = jfc.getSelectedFont();
					for (int i = 0; i < lp.getTabCount(); i++)
						if (lp.getComponentAt(i) instanceof BarChartPanel) {
							BarChartPanel bcp = (BarChartPanel) lp
									.getComponentAt(i);
							((CategoryPlot) bcp.getChart().getPlot())
									.getDomainAxis().setTickLabelFont(
											fontOfDomains);
							bcp.repaint();
						}
				}
			}
		});
		// panel.add(domainFontSetter, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		apply = new JButton("نمایش جدول");
		apply.setFont(font);
		panel.add(apply, gbc);

		// Taher
		gbc.gridy++;
		print = new JButton("پرینت");
		print.setEnabled(false);
		print.setFont(font);
		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (lp.getComponentAt(lp.getSelectedIndex()) instanceof BarChartPanel) {
					BarChartPanel temp = (BarChartPanel) lp.getComponentAt(lp
							.getSelectedIndex());
					temp.createChartPrintJob();
				} else if (lp.getComponentAt(lp.getSelectedIndex()) instanceof FrequencyTablePanel) {
					FrequencyTablePanel temp = (FrequencyTablePanel) lp
							.getComponentAt(lp.getSelectedIndex());
					try {
						temp.table.print(JTable.PrintMode.FIT_WIDTH);
					} catch (PrinterException ex) {
						Logger.getLogger(ReportPanelV2.class.getName()).log(
								Level.SEVERE, null, ex);
					}
				} else {
					ResizableTablePanel temp = (ResizableTablePanel) lp
							.getComponentAt(lp.getSelectedIndex());
					try {
						temp.tablePanel.table.print(JTable.PrintMode.FIT_WIDTH);
					} catch (PrinterException ex) {
						Logger.getLogger(ReportPanelV2.class.getName()).log(
								Level.SEVERE, null, ex);
					}
				}
			}
		});
		panel.add(print, gbc);

		gbc.gridy++;
		pdf = new JButton("PDF");
		pdf.setEnabled(false);
		pdf.setFont(new Font("arial", Font.BOLD, 14));
		pdf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (lp.getSelectedIndex() != -1
						&& lp.getComponentAt(lp.getSelectedIndex()) != null) {
					if (ReportPanelV2.this.lp.getComponentAt(lp
							.getSelectedIndex()) instanceof BarChartPanel) {
						BarChartPanel temp = (BarChartPanel) ReportPanelV2.this.lp
								.getComponentAt(lp.getSelectedIndex());
						try {
							Png2Pdf a = new Png2Pdf();
							a.convert(false, ReportPanelV2.this.lp
									.getTitleAt(lp.getSelectedIndex()), temp
									.doSaveAs(null));
						} catch (IOException ex) {
							Logger.getLogger(ReportPanelV2.class.getName())
									.log(Level.SEVERE, null, ex);
						}
					} else if (ReportPanelV2.this.lp.getComponentAt(lp
							.getSelectedIndex()) instanceof FrequencyTablePanel) {
						FrequencyTablePanel temp = (FrequencyTablePanel) ReportPanelV2.this.lp
								.getComponentAt(lp.getSelectedIndex());
						try {
							Png2Pdf a = new Png2Pdf();
							a.convert(false, ReportPanelV2.this.lp
									.getTitleAt(lp.getSelectedIndex()), temp
									.doSaveAs(null));
						} catch (IOException ex) {
							Logger.getLogger(ReportPanelV2.class.getName())
									.log(Level.SEVERE, null, ex);
						}
					} else {
						ResizableTablePanel temp = (ResizableTablePanel) ReportPanelV2.this.lp
								.getComponentAt(lp.getSelectedIndex());
						try {
							temp.tablePanel.doSaveAs(null);
						} catch (IOException ex) {
							Logger.getLogger(ReportPanelV2.class.getName())
									.log(Level.SEVERE, null, ex);
						}
					}
				}
			}
		});
		panel.add(pdf, gbc);

		gbc.gridy++;
		excel = new JButton("ذخیره ی رکوردهای انتخاب شده");
		excel.setEnabled(false);
		excel.setFont(font);
		excel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (lp.getComponentAt(lp.getSelectedIndex()) instanceof ResizableTablePanel) {
					ResizableTablePanel rtp = (ResizableTablePanel) lp
							.getComponentAt(lp.getSelectedIndex());
					Record[] records = new Record[rtp.tablePanel
							.getSelectedRows().size()];
					for (int i = 0; i < rtp.tablePanel.getSelectedRows().size(); i++)
						records[i] = rowToRecord(rtp.tablePanel
								.getSelectedRows().get(i));
					parent.saveRecords(records);
				}
			}
		});
		panel.add(excel, gbc);

		gbc.gridy++;
		JButton close = new JButton("بستن گزارش");
		close.setFont(font);
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ReportPanelV2.this.parent
						.action(RightPanelLabel.CMND_CLOSE_TAB);
			}
		});
		panel.add(close, gbc);

		gbc.gridx = -1;
		gbc.gridy++;
		gbc.weighty = 0.4;
		panel.add(new JLabel(), gbc);

		JButton bHide = new JButton(new ImageIcon("./Files/Images/Hide.png"));
		bHide.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.weightx = 1;
		gbc.weighty = 0.2;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.fill = GridBagConstraints.NONE;
		panel.add(bHide, gbc);
		bHide.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				removeAll();
				repaint();

				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridx = 0;
				gbc.gridy = 1;
				gbc.weightx = 0.2;
				gbc.weighty = 0.2;
				gbc.anchor = GridBagConstraints.LINE_START;
				JButton bShow = new JButton(new ImageIcon(
						"./Files/Images/Show.png"));
				bShow.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				ReportPanelV2.this.add(bShow, gbc);
				bShow.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						removeAll();
						repaint();
						showLP();
					}
				});

				gbc.gridx = 1;
				gbc.gridy = 1;
				gbc.weightx = 0.8;
				gbc.weighty = 0.2;
				add(new JLabel(), gbc);

				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.gridwidth = 2;
				gbc.gridheight = 3;
				gbc.weightx = 1;
				gbc.weighty = 1;
				gbc.fill = GridBagConstraints.BOTH;
				ReportPanelV2.this.add(lp, gbc);
			}
		});

		gbc.gridx = -1;
		gbc.gridy++;
		gbc.weighty = 0.4;
		panel.add(new JLabel(), gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		JLabel tmpL = new JLabel("فیلترها", JLabel.CENTER) {

			private static final long serialVersionUID = -8465693046450570229L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				g.drawLine(0, getHeight() / 2, getWidth() / 2 - 27,
						getHeight() / 2);
				g.drawLine(getWidth() / 2 + 27, getHeight() / 2, getWidth(),
						getHeight() / 2);
			}
		};
		tmpL.setFont(font);
		tmpL.setForeground(new Color(76, 101, 147));
		panel.add(tmpL, gbc);

		apply.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showRecords();
			}
		});

		return jsp;
	}

	private JTabbedPane makeLPanel() {
		JTabbedPane jtp = new JTabbedPane();
		return jtp;
	}

	private void makeFilterList(Container container, GridBagConstraints gbc) {
		info = new String[17];
		infoNum = new String[17];
		info[0] = " فراوانی هر درجه ریسک در" + " QEC Level ";
		info[1] = " فراوانی درجه ریسک به تفکیک سالن تولیدی";
		info[2] = " فراوانی ریسک بالا در سالن ها";
		info[3] = " فراوانی ریسک بالا در ناحیه کمر در سالن ها";
		info[4] = " فراوانی ریسک بالا در ناحیه شانه و بازو در سالن ها";
		info[5] = " فراوانی ریسک بالا در ناحیه گردن در سالن ها";
		info[6] = " فراوانی ریسک بالا در ناحیه مچ و دست در سالن ها";
		info[7] = " مشاغل دارای امتیاز QEC Level بالا";
		info[8] = " مشاغل دارای امتیاز ریسک بالا در ناحیه کمر";
		info[9] = " مشاغل دارای امتیاز ریسک بالا در ناحیه شانه و بازو";
		info[10] = " مشاغل دارای امتیاز ریسک بالا در ناحیه گردن";
		info[11] = " مشاغل دارای امتیاز ریسک بالا در ناحیه مچ و دست";
		info[12] = " فرایندهای کاری دارای اقدام اصلاحی";
		info[13] = " فراوانی مشاغل دارای فرایند حمل بار";
		info[14] = " مشاغل دارای حمل بار با ریسک بالا به تفکیک سالن";
		info[15] = " مشاغل دارای ریسک بالا بدون اقدام اصلاحی";
		info[16] = " مشاغل با ریسک بالا به تفکیک گردش شغلی";
		infoNum[0] = "1-";
		infoNum[1] = "2-";
		infoNum[2] = "3-";
		infoNum[3] = "4-";
		infoNum[4] = "5-";
		infoNum[5] = "6-";
		infoNum[6] = "7-";
		infoNum[7] = "8-";
		infoNum[8] = "9-";
		infoNum[9] = "10-";
		infoNum[10] = "11-";
		infoNum[11] = "12-";
		infoNum[12] = "13-";
		infoNum[13] = "14-";
		infoNum[14] = "15-";
		infoNum[15] = "16-";
		infoNum[16] = "17-";
		filterList = new JComboBox<String>();
		filterList.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		for (int a = 0; a < info.length; a++) {
			String s = infoNum[a] + info[a];
			filterList.addItem(s);
		}
		filterList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] salons = null;
				switch (filterList.getSelectedIndex()) {
				case 0:
					filters.setEnabled(false);
					filters.removeAllItems();
					filters.addItem("فیلتر ندارد");
					reassessment.setEnabled(true);
					filters.setForeground(Color.DARK_GRAY);
					break;
				case 1:
					filters.setEnabled(true);
					filters.removeAllItems();
					salons = parent.getLogic().getSalonNames();
					filters.addItem("همه");
					for (int i = 0; i < salons.length; i++)
						filters.addItem(salons[i]);
					filters.setForeground(Color.DARK_GRAY);
					break;
				case 2:
					filters.setEnabled(false);
					filters.removeAllItems();
					filters.addItem("فیلتر ندارد");
					reassessment.setEnabled(true);
					filters.setForeground(Color.DARK_GRAY);
					break;
				case 3:
					filters.setEnabled(false);
					filters.removeAllItems();
					filters.addItem("فیلتر ندارد");
					reassessment.setEnabled(true);
					filters.setForeground(Color.DARK_GRAY);
					break;
				case 4:
					filters.setEnabled(false);
					filters.removeAllItems();
					filters.addItem("فیلتر ندارد");
					reassessment.setEnabled(true);
					filters.setForeground(Color.DARK_GRAY);
					break;
				case 5:
					filters.setEnabled(false);
					filters.removeAllItems();
					filters.addItem("فیلتر ندارد");
					reassessment.setEnabled(true);
					filters.setForeground(Color.DARK_GRAY);
					break;
				case 6:
					filters.setEnabled(false);
					filters.removeAllItems();
					filters.addItem("فیلتر ندارد");
					reassessment.setEnabled(true);
					filters.setForeground(Color.DARK_GRAY);
					break;
				case 7:
					filters.setEnabled(true);
					filters.removeAllItems();
					salons = parent.getLogic().getSalonNames();
					filters.addItem("همه");
					for (int i = 0; i < salons.length; i++)
						filters.addItem(salons[i]);
					reassessment.setEnabled(true);
					filters.setForeground(Color.DARK_GRAY);
					break;
				case 8:
					filters.setEnabled(true);
					filters.removeAllItems();
					salons = parent.getLogic().getSalonNames();
					filters.addItem("همه");
					for (int i = 0; i < salons.length; i++)
						filters.addItem(salons[i]);
					reassessment.setEnabled(true);
					filters.setForeground(Color.DARK_GRAY);
					break;
				case 9:
					filters.setEnabled(true);
					filters.removeAllItems();
					salons = parent.getLogic().getSalonNames();
					filters.addItem("همه");
					for (int i = 0; i < salons.length; i++)
						filters.addItem(salons[i]);
					reassessment.setEnabled(true);
					filters.setForeground(Color.DARK_GRAY);
					break;
				case 10:
					filters.setEnabled(true);
					filters.removeAllItems();
					salons = parent.getLogic().getSalonNames();
					filters.addItem("همه");
					for (int i = 0; i < salons.length; i++)
						filters.addItem(salons[i]);
					reassessment.setEnabled(true);
					filters.setForeground(Color.DARK_GRAY);
					break;
				case 11:
					filters.setEnabled(true);
					filters.removeAllItems();
					salons = parent.getLogic().getSalonNames();
					filters.addItem("همه");
					for (int i = 0; i < salons.length; i++)
						filters.addItem(salons[i]);
					reassessment.setEnabled(true);
					filters.setForeground(Color.DARK_GRAY);
					break;
				case 12:
					filters.setEnabled(false);
					filters.removeAllItems();
					filters.addItem("فیلتر ندارد");
					filters.setForeground(Color.DARK_GRAY);
					break;
				case 13:
					reassessment.setEnabled(true);
					filters.setEnabled(false);
					filters.removeAllItems();
					filters.addItem("فیلتر ندارد");
					filters.setForeground(Color.DARK_GRAY);
					break;
				case 14:
					filters.setEnabled(true);
					filters.removeAllItems();
					salons = parent.getLogic().getSalonNames();
					filters.addItem("همه");
					for (int i = 0; i < salons.length; i++)
						filters.addItem(salons[i]);
					filters.setForeground(Color.DARK_GRAY);
					reassessment.setEnabled(true);
					break;
				case 15:
					filters.setEnabled(false);
					filters.removeAllItems();
					filters.addItem("فیلتر ندارد");
					filters.setForeground(Color.DARK_GRAY);
					reassessment.setEnabled(true);
					break;
				case 16:
					filters.setEnabled(true);
					filters.removeAllItems();
					filters.addItem("همه");
					filters.addItem("روزانه");
					filters.addItem("ثابت");
					filters.addItem("هفتگی");
					filters.setForeground(Color.DARK_GRAY);
					reassessment.setEnabled(true);
					break;
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;

		container.add(filterList, gbc);
	}

	private static JPanel getTitlePanel(final JTabbedPane tabbedPane,
			final JPanel panel, String title) {
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		titlePanel.setOpaque(false);
		JLabel titleLbl = new JLabel(title);
		titleLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		titlePanel.add(titleLbl);
		JButton closeButton = new JButton("x");
		closeButton.setBorder(BorderFactory.createEmptyBorder(2, 10, 5, 10));

		closeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.remove(panel);
			}
		});
		titlePanel.add(closeButton);

		return titlePanel;
	}

	private void showRecords() {
		parent.setDeleting(true);
		print.setEnabled(true);
		pdf.setEnabled(true);
		makeChart();
		// MAKING NAME: START
		String name = null;
		if (!filters.isEnabled())
			name = (reassessment.isSelected() ? "(مجدد) " : "") + "جدول"
					+ info[filterList.getSelectedIndex()];
		else
			name = (reassessment.isSelected() ? "(مجدد) - " : "") + "جدول"
					+ info[filterList.getSelectedIndex()] + " - "
					+ (String) filters.getSelectedItem();
		if (name.length() > 38)
			name = name.substring(0, 34) + "...";
		// MAKING NAME: END
		// ////////////////////////////////////////////////////
		if (filterList.getSelectedIndex() == 0) {
			try {
				String[] data = { "خیلی بالا", "بالا", "متوسط", "پایین" };
				String[] values = new String[4];
				values[0] = parent.getLogic().report(
						filterList.getSelectedIndex() + 1, "خیلی بالا",
						reassessment.isSelected()).length
						+ "";
				values[1] = parent.getLogic().report(
						filterList.getSelectedIndex() + 1, "بالا",
						reassessment.isSelected()).length
						+ "";
				values[2] = parent.getLogic().report(
						filterList.getSelectedIndex() + 1, "متوسط",
						reassessment.isSelected()).length
						+ "";
				values[3] = parent.getLogic().report(
						filterList.getSelectedIndex() + 1, "پایین",
						reassessment.isSelected()).length
						+ "";
				FrequencyTablePanel tablePanel = new FrequencyTablePanel(data,
						values);
				tablePanel
						.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				lp.addTab(name, null, tablePanel, name);
				lp.setTabComponentAt(lp.indexOfComponent(tablePanel),
						getTitlePanel(lp, tablePanel, name));
				lp.setSelectedIndex(lp.getTabCount() - 1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// ////////////////////////////////////////////////////
		else if (filterList.getSelectedIndex() == 1) {
			try {
				String[] data = { "خیلی بالا", "بالا", "متوسط", "پایین" };
				String[] values = new String[4];
				values[0] = parent.getLogic().report(
						2,
						(String) (filters.getSelectedItem()) + ";"
								+ "خیلی بالا", reassessment.isSelected()).length
						+ "";
				values[1] = parent.getLogic().report(2,
						(String) (filters.getSelectedItem()) + ";" + "بالا",
						reassessment.isSelected()).length
						+ "";
				values[2] = parent.getLogic().report(2,
						(String) (filters.getSelectedItem()) + ";" + "متوسط",
						reassessment.isSelected()).length
						+ "";
				values[3] = parent.getLogic().report(2,
						(String) (filters.getSelectedItem()) + ";" + "پایین",
						reassessment.isSelected()).length
						+ "";
				FrequencyTablePanel tablePanel = new FrequencyTablePanel(data,
						values);
				tablePanel
						.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				lp.addTab(name, null, tablePanel, name);
				lp.setTabComponentAt(lp.indexOfComponent(tablePanel),
						getTitlePanel(lp, tablePanel, name));
				lp.setSelectedIndex(lp.getTabCount() - 1);
			} catch (SQLException e) {
				System.err.println("SQLException");
			}
		}
		// ////////////////////////////////////////////////////
		else if (2 <= filterList.getSelectedIndex()
				&& filterList.getSelectedIndex() <= 6) {
			try {
				String[] data = parent.getLogic().getSalonNames();
				String[] values = new String[data.length];
				for (int i = 0; i < data.length; i++)
					values[i] = parent.getLogic().report(
							filterList.getSelectedIndex() + 1, data[i],
							reassessment.isSelected()).length
							+ "";
				FrequencyTablePanel tablePanel = new FrequencyTablePanel(data,
						values);
				tablePanel
						.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				lp.addTab(name, null, tablePanel, name);
				lp.setTabComponentAt(lp.indexOfComponent(tablePanel),
						getTitlePanel(lp, tablePanel, name));
				lp.setSelectedIndex(lp.getTabCount() - 1);
			} catch (SQLException e) {
				System.err.println("SQLException");
			}
		}
		// ////////////////////////////////////////////////////
		if (filterList.getSelectedIndex() > 6
				&& filterList.getSelectedIndex() < 13) {
			ResizableTablePanel tablePanel = new ResizableTablePanel();
			tablePanel
					.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			tablePanel.tablePanel.setDataHandler(this);
			Record[] records = null;
			try {
				records = parent.getLogic().report(
						filterList.getSelectedIndex() + 1,
						(String) (filters.getSelectedItem()),
						reassessment.isSelected());
			} catch (SQLException e) {
				System.err.println("SQLException");
			}

			for (Record e : records) {
				Object[] r = new Object[tablePanel.tablePanel.table
						.getColumnCount()];
				r[0] = false;
				String[] ee = e.toStringArray();
				for (int y = 1; y < tablePanel.tablePanel.table
						.getColumnCount() - 1; y++)
					r[y] = ee[y - 1];
				r[tablePanel.tablePanel.table.getColumnCount() - 1] = true;
				tablePanel.tablePanel.tableModel.addRow(r);
			}

			lp.addTab(name, null, tablePanel, name);
			lp.setTabComponentAt(lp.indexOfComponent(tablePanel),
					getTitlePanel(lp, tablePanel, name));
			lp.setSelectedIndex(lp.getTabCount() - 1);
		}
		// ////////////////////////////////////////////////////
		else if (filterList.getSelectedIndex() == 13) {
			try {
				String[] data = { "خیلی بالا", "بالا", "متوسط", "پایین" };
				String[] values = new String[4];
				values[0] = parent.getLogic().report(
						filterList.getSelectedIndex() + 1,
						"خیلی بالا" + ";" + "همه", reassessment.isSelected()).length
						+ "";
				values[1] = parent.getLogic().report(
						filterList.getSelectedIndex() + 1,
						"بالا" + ";" + "همه", reassessment.isSelected()).length
						+ "";
				values[2] = parent.getLogic().report(
						filterList.getSelectedIndex() + 1,
						"متوسط" + ";" + "همه", reassessment.isSelected()).length
						+ "";
				values[3] = parent.getLogic().report(
						filterList.getSelectedIndex() + 1,
						"پایین" + ";" + "همه", reassessment.isSelected()).length
						+ "";
				FrequencyTablePanel tablePanel = new FrequencyTablePanel(data,
						values);
				tablePanel
						.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				lp.addTab(name, null, tablePanel, name);
				lp.setTabComponentAt(lp.indexOfComponent(tablePanel),
						getTitlePanel(lp, tablePanel, name));
				lp.setSelectedIndex(lp.getTabCount() - 1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// ////////////////////////////////////////////////////
		parent.setDeleting(false);
	}

	private void makeChart() {
		BarChartPanel chartPanel;
		String name = "نمودار" + info[(filterList.getSelectedIndex())];
		if (name.length() > 38)
			name = name.substring(0, 34) + "...";
		switch (filterList.getSelectedIndex()) {
		case 0:
			chartPanel = makeChart0("فراوانی هر درجه ریسک");
			break;
		case 1:
			chartPanel = makeChart1("فراوانی هر درجه ریسک به تفکیک سالن");
			break;
		case 2:
			chartPanel = makeChart2("فراوانی درجه ریسک بالا به تفکیک نوع سالن");
			break;
		case 3:
			chartPanel = makeChart3("فراوانی ریسک بالا در ناحیه کمر به تفکیک سالن");
			break;
		case 4:
			chartPanel = makeChart4("فراوانی ریسک بالا در ناحیه شانه و بازو به تفکیک سالن");
			break;
		case 5:
			chartPanel = makeChart5("فراوانی ریسک بالا در ناحیه گردن به تفکیک سالن");
			break;
		case 6:
			chartPanel = makeChart6("فراوانی ریسک بالا در ناحیه مچ و دست به تفکیک سالن");
			break;
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
			chartPanel = null; // No Chart
			break;
		case 13:
			chartPanel = makeChart13("فراوانی مشاغل دارای فرایند حمل بار");
			break;
		case 14:
			chartPanel = makeChart14("مشاغل دارای حمل بار با ریسک بالا به تفکیک سالن");
			break;
		case 15:
			chartPanel = null; // No Chart
			break;
		case 16:
			chartPanel = makeChart16("مشاغل با ریسک بالا به تفکیک گردش شغلی");
			break;
		default:
			chartPanel = null;
		}
		if (chartPanel != null) {
			chartPanel.getChart().getTitle().setFont(fontOfHeaders);
			((CategoryPlot) chartPanel.getChart().getPlot()).getDomainAxis()
					.setTickLabelFont(fontOfDomains);
			lp.addTab(name, null, chartPanel, name);
			lp.setTabComponentAt(lp.indexOfComponent(chartPanel),
					getTitlePanel(lp, chartPanel, name));
		}
	}

	private BarChartPanel makeChart0(String title) {
		String[] horizontalAxis = { "درجه ریسک" };
		String[] columns = { "پایین", "متوسط", "بالا", "خیلی بالا" };
		int[][] values = DataMaker.qecLevelValues(reassessment.isSelected());
		BarChartPanel chart = new BarChartPanel(title, "درجه ریسک", "فراوانی",
				horizontalAxis, columns, values);
		BarRenderer br = (BarRenderer) (((CategoryPlot) (chart.getChart()
				.getPlot())).getRenderer());
		br.setSeriesPaint(0, Color.GREEN);
		br.setSeriesPaint(1, Color.BLUE);
		br.setSeriesPaint(2, Color.ORANGE);
		br.setSeriesPaint(3, Color.RED);
		return chart;
	}

	private BarChartPanel makeChart1(String title) {
		String[] horizontalAxis = parent.getLogic().getSalonNames();
		String[] columns = { "پایین", "متوسط", "بالا", "خیلی بالا" };
		int[][] values = DataMaker.qecLevelValuesBySalons(horizontalAxis,
				reassessment.isSelected());
		BarChartPanel chart = new BarChartPanel(title, "سالن", "فراوانی",
				horizontalAxis, columns, values);
		BarRenderer br = (BarRenderer) (((CategoryPlot) (chart.getChart()
				.getPlot())).getRenderer());
		br.setSeriesPaint(0, Color.GREEN);
		br.setSeriesPaint(1, Color.BLUE);
		br.setSeriesPaint(2, Color.ORANGE);
		br.setSeriesPaint(3, Color.RED);
		return chart;
	}

	private BarChartPanel makeChart2(String title) {
		String[] horizontalAxis = parent.getLogic().getSalonNames();
		String[] columns = { "بالا" };
		int[][] values = DataMaker.qecLevelValuesByKindOfSalons(parent
				.getLogic().getSalonNames(), reassessment.isSelected());
		return new BarChartPanel(title, "سالن", "فراوانی", horizontalAxis,
				columns, values);
	}

	private BarChartPanel makeChart3(String title) {
		String[] horizontalAxis = parent.getLogic().getSalonNames();
		String[] columns = { "بالا" };
		int[][] values = DataMaker.backLevelValuesBySalons(horizontalAxis,
				reassessment.isSelected());
		return new BarChartPanel(title, "سالن", "فراوانی", horizontalAxis,
				columns, values);
	}

	private BarChartPanel makeChart4(String title) {
		String[] horizontalAxis = parent.getLogic().getSalonNames();
		String[] columns = { "بالا" };
		int[][] values = DataMaker.shoulderLevelValuesBySalons(horizontalAxis,
				reassessment.isSelected());
		return new BarChartPanel(title, "سالن", "فراوانی", horizontalAxis,
				columns, values);
	}

	private BarChartPanel makeChart5(String title) {
		String[] horizontalAxis = parent.getLogic().getSalonNames();
		String[] columns = { "بالا" };
		int[][] values = DataMaker.neckLevelValuesBySalons(horizontalAxis,
				reassessment.isSelected());
		return new BarChartPanel(title, "سالن", "فراوانی", horizontalAxis,
				columns, values);
	}

	private BarChartPanel makeChart6(String title) {
		String[] horizontalAxis = parent.getLogic().getSalonNames();
		String[] columns = { "بالا" };
		int[][] values = DataMaker.wristLevelValuesBySalons(horizontalAxis,
				reassessment.isSelected());
		return new BarChartPanel(title, "سالن", "فراوانی", horizontalAxis,
				columns, values);
	}

	private BarChartPanel makeChart13(String title) {
		String[] horizontalAxis = parent.getLogic().getSalonNames();
		String[] columns = { "فراوانی" };
		int[][] values = DataMaker.numberOfWorksWithHasBar(parent.getLogic()
				.getSalonNames(), reassessment.isSelected());
		return new BarChartPanel(title, "درجه ریسک", "فراوانی", horizontalAxis,
				columns, values);
	}

	private BarChartPanel makeChart14(String title) {
		String[] horizontalAxis = parent.getLogic().getSalonNames();
		String[] columns = { "بالا" };
		int[][] values = DataMaker
				.numberOfHighQECWorksWithHasBarByKindOfSalons(parent.getLogic()
						.getSalonNames(), reassessment.isSelected());
		return new BarChartPanel(title, "سالن", "فراوانی", horizontalAxis,
				columns, values);
	}

	private BarChartPanel makeChart16(String title) {
		String[] horizontalAxis = parent.getLogic().getJobRotations();
		String[] columns = { "فراوانی" };
		int[][] values = DataMaker
				.numberOfHighQECWorksByJobRotation(reassessment.isSelected());
		return new BarChartPanel(title, "گردش شغلی", "فراوانی", horizontalAxis,
				columns, values);
	}

	@Override
	public boolean getDeleting() {
		return false;
	}

	@Override
	public boolean isEditable(int row, int col) {
		if (col == 0)
			return true;
		else
			return false;
	}

	@Override
	public void dataEdited() {
		System.err.println("Something is wrong.");
	}

	public Record rowToRecord(int row) {
		if (lp.getComponentAt(lp.getSelectedIndex()) instanceof ResizableTablePanel) {
			ResizableTablePanel rtp = (ResizableTablePanel) lp
					.getComponentAt(lp.getSelectedIndex());
			Object[] rowData = new Object[rtp.tablePanel.tableModel
					.getColumnCount()];
			ArrayList<String> rowSrtings = new ArrayList<String>();
			rowData = rtp.tablePanel.data.get(row);
			for (int i = 1; i < rtp.tablePanel.tableModel.getColumnCount() - 1; i++) {
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
		} else
			return null;
	}

}

class AdaptedRadioButton extends JRadioButton {

	private static final long serialVersionUID = 3082099202242853533L;
	public static final int SHOW_LIST = 100;
	public static final int SHOW_DIAGRAM = 101;
	public static final int DOT_DIAGRAM = 1011;
	public static final int PIPE_DIAGRAM = 1012;
	public static final int REASSESSMENT = 200;
	private static final Image on = (new ImageIcon("./Files/Images/CheckOn.png"))
			.getImage();
	private static final Image on2 = (new ImageIcon(
			"./Files/Images/CheckOn2.png")).getImage();
	private static final Image off = (new ImageIcon(
			"./Files/Images/CheckOff.png")).getImage();

	private JLabel sideLabel;
	private JPanel panel;
	private String info;
	private int id;

	public AdaptedRadioButton(String info, int id, Container container,
			Font font, GridBagConstraints gbc) {
		this.id = id;
		this.info = info;
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints miniGBC = new GridBagConstraints();

		miniGBC.gridx = 1;
		miniGBC.gridy = 0;
		panel.add(this, miniGBC);
		miniGBC.fill = GridBagConstraints.HORIZONTAL;
		miniGBC.gridx = 0;
		miniGBC.gridy = 0;
		miniGBC.weightx = 1;
		sideLabel = new JLabel(info, JLabel.RIGHT);
		sideLabel.setFont(font);
		panel.add(sideLabel, miniGBC);

		container.add(panel, gbc);
	}

	@Override
	public void paint(Graphics g) {
		switch (id) {
		case SHOW_LIST:
		case SHOW_DIAGRAM:
			if (isSelected()) {
				g.drawImage(on, 2, 3, getWidth() - 4, getWidth() - 4, null);
			} else {
				g.drawImage(off, 2, 3, getWidth() - 4, getWidth() - 4, null);
			}
			break;
		default:
			if (isSelected()) {
				g.drawImage(on2, 2, 3, getWidth() - 4, getWidth() - 4, null);
			} else {
				g.drawImage(off, 2, 3, getWidth() - 4, getWidth() - 4, null);
			}
		}
	}

	@Override
	public void setEnabled(boolean b) {
		sideLabel.setEnabled(b);
		super.setEnabled(b);
	}

	public String getInfo() {
		return info;
	}

}
