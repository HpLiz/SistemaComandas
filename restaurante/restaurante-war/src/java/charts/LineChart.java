/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package charts;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import manipuladatos.MDPedido;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author crist
 */
@Named(value = "lineChart")
@SessionScoped
public class LineChart implements Serializable {

    @EJB
    private MDPedido mDPedido;

    /**
     * Creates a new instance of LineChart
     */
    private LineChartModel model;

    public LineChart() {
        model = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        //graficaTipos();

    }

    public LineChartModel getModel() {
        return model;
    }

    public LineChartModel modeloTipos() {
        LineChartModel modeln = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        List<Object[]> datos = new ArrayList<Object[]>();
        try {
            // Obtener la fecha actual
            LocalDate fechaActual = LocalDate.now();

            // Obtener la fecha de un mes atrás
            LocalDate fechaUnMesAtras = fechaActual.minusMonths(1);

            // Formatear las fechas
            Date ff = Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date fi = Date.from(fechaUnMesAtras.atStartOfDay(ZoneId.systemDefault()).toInstant());
            System.out.println("ff" + ff);
            System.out.println("fi" + fi);

            datos = mDPedido.cantidad_por_periodo_y_tipo(fi, ff);
            System.out.println("tamaño" + datos.size());
            System.out.println("tamaño" + datos.toString());
            System.out.println("datos" + datos.get(0)[0] + "|" + datos.get(0)[1] + "|" + datos.get(0)[2] + "|");
            System.out.println("datos" + datos.get(1)[0] + "|" + datos.get(1)[1] + "|" + datos.get(1)[2] + "|");
            System.out.println("datos" + datos.get(2)[0] + "|" + datos.get(2)[1] + "|" + datos.get(2)[2] + "|");

        } catch (Exception e) {
            System.out.println("Error al recuperar" + e);
        }

        series1.setLabel("Comidas");
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Bebidas");
        LineChartSeries series3 = new LineChartSeries();
        series3.setLabel("Postres");

        // Mapa para asociar fechas con índices
        Map<Date, Integer> fechaIndiceMap = new HashMap<>();
        int aux = 1;
        for (Object[] d : datos) {
            Date fecha = (Date) d[0];
            if (!fechaIndiceMap.containsKey(fecha)) {
                fechaIndiceMap.put((Date)d[0], aux);
                aux++;
            }
        }

        for (Object[] d : datos) {
            Date fecha = (Date) d[0];
            Integer indice = fechaIndiceMap.get(fecha);
            if (d[1].equals("Comida")) {
                series1.set(indice, ((Number) d[2]).intValue());
            } else if (d[1].equals("Bebida")) {
                series2.set(indice, ((Number) d[2]).intValue());
            } else if (d[1].equals("Postre")) {
                series3.set(indice, ((Number) d[2]).intValue());
            }

        }
        modeln.addSeries(series1);
        modeln.addSeries(series2);
        modeln.addSeries(series3);
        modeln.setTitle("Ventas por tipo");
        modeln.setLegendPosition("e");
        Axis yAxis = modeln.getAxis(AxisType.Y);
        yAxis.setMin(0);

        return modeln;

    }
}
