package com.gestaltchart.chart.line;

import java.awt.Color;
import java.awt.Graphics2D;

import org.apache.commons.collections4.CollectionUtils;

import com.gestaltchart.chart.Chart;

public class LineChart extends Chart {

    public LineChart(int canvasWidth, int canvasHeight) {
        super(canvasWidth, canvasHeight);
    }
    
    public LineChart(int canvasWidth, int canvasHeight, String title) {
        super(canvasWidth, canvasHeight, title);
    }
    
    @Override
    public void writeToGraphicsChartData(Graphics2D graphics2D) {
        if (!CollectionUtils.isEmpty(this.getSeriesList())) {
            graphics2D.setColor(Color.BLUE.brighter());
            graphics2D.drawLine(20, 100, 120, 100);
            // TODO complet with working code.
        }
    }
}
