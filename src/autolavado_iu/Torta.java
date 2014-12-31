/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autolavado_iu;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;


public class Torta extends JFrame {

  private static final long serialVersionUID = 1L;

  public Torta(int x,int y,String tipoContenido,String primer,String segundo,int socio,int noSocio) {
        super("Graficos Estadisticos");
        PieDataset dataset = createDataset(primer,segundo,socio,noSocio);
        JFreeChart chart = createChart(dataset,tipoContenido);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
        setLocation(x, y);
        pack();
        setVisible(true);
    }
    
    private  PieDataset createDataset(String primer,String segundo,int socio, int noSocio) {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue(primer+" : "+socio, socio);
        result.setValue(segundo+" : "+noSocio, noSocio);
        return result;
    }
    
    private JFreeChart createChart(PieDataset dataset, String title) {
        
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;
    }
    
} 