package graphicUI.chart;

import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartPanel extends ChartPanel {
	private static final long serialVersionUID = -3385337748614783264L;

	public BarChartPanel(String header, String horizontalInfo,
			String verticalInfo, String[] horizontalAxis, String[] columns,
			int[][] values) {

		super(initChart(header, horizontalInfo, verticalInfo, horizontalAxis,
				columns, values));

		CategoryPlot plot = (CategoryPlot) getChart().getPlot();
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions
				.createUpRotationLabelPositions(Math.PI / 10.0));

		CategoryPlot catPlot = (CategoryPlot) getChart().getPlot();
		BarRenderer renderer = (BarRenderer) catPlot.getRenderer();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator(
				"{2}", NumberFormat.getInstance()));
		renderer.setBaseItemLabelsVisible(true);
		repaint();
	}

	private static JFreeChart initChart(String header, String horizontalInfo,
			String verticalInfo, String[] horizontalAxis, String[] columns,
			int[][] values) {
		CategoryDataset dataset = createDataset(horizontalAxis, columns, values);
		return createChart(header, horizontalInfo, verticalInfo, dataset);
	}

	private static CategoryDataset createDataset(String[] horizontalAxis,
			String[] columns, int[][] values) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < horizontalAxis.length; i++)
			for (int j = 0; j < columns.length; j++)
				dataset.addValue(values[j][i], columns[j], horizontalAxis[i]);
		return dataset;
	}

	private static JFreeChart createChart(String header, String horizontalInfo,
			String verticalInfo, CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart(header, horizontalInfo,
				verticalInfo, dataset, PlotOrientation.VERTICAL, true, true,
				false);
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.getRangeAxis().setStandardTickUnits(
				NumberAxis.createIntegerTickUnits());
		if (dataset.getRowCount() == 1)
			chart.removeLegend();
		return chart;
	}

}
