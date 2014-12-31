package autolavado_iu;

import java.awt.Color;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/*
 * @author VladySP
 */
public class Barras extends JFrame{
    
    public Barras(int x,int y,String tipoContenido,String primer,String segundo,int socio,int noSocio,String numeral,String tipos){
        super("Graficos Estadisticos");
        
        DefaultCategoryDataset dataset = createDataset(primer,segundo,socio,noSocio);
        JFreeChart chart = createChart(dataset,tipoContenido,numeral,tipos);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
        setLocation(x, y);
        pack();
        setVisible(true);
    }
    
    private  DefaultCategoryDataset createDataset(String primer,String segundo,int tipoUno, int tipoDos) {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        result.addValue(tipoUno,primer,primer);
        result.addValue(tipoDos,segundo,segundo);
        return result;
    }
    
    private JFreeChart createChart(DefaultCategoryDataset dataset, String title,String numeral,String tipos) {
        
        JFreeChart chart = ChartFactory.createBarChart3D(
            title, //Título de la gráfica
            numeral, //leyenda Eje horizontal
            tipos, //leyenda Eje vertical
            dataset, //datos
            PlotOrientation.VERTICAL, //orientación
            true, //incluir leyendas
            true, //mostrar tooltips
            true);
        chart.setBackgroundPaint (Color.LIGHT_GRAY);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        plot.setBackgroundPaint (Color.WHITE); //fondo del grafico
        plot.setDomainGridlinesVisible (true);//lineas de rangos, visibles
        plot.setRangeGridlinePaint (Color.BLACK);//color de las lineas de rangos
        return chart;
    }
    public static void main(String...args){
        
    }
}
