package Grafico;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.JFrame;

public class Grafico extends JFrame {
    public Grafico(double v1, double v2, double v3) {
        super("Gráfico");
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("FIFO", v1);
        dataset.setValue("LRU", v2);
        dataset.setValue("OTIMO", v3);
        JFreeChart chart = ChartFactory.createPieChart(
                "FaultPages entre os algoritmos",
                dataset,
                true,
                true,
                false);
        ChartPanel chartPanel = new ChartPanel(chart);
        this.add(chartPanel);
    }
}