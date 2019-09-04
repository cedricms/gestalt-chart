package com.gestaltchart.chart.line;

import java.awt.Color;
import java.awt.Graphics2D;

import com.gestaltchart.chart.Chart;

public class LineChart extends Chart {

    public LineChart(int canvasWidth, int canvasHeight) {
        super(canvasWidth, canvasHeight);
    }
    
    public LineChart(int canvasWidth, int canvasHeight, String title) {
        super(canvasWidth, canvasHeight, title);
    }
    
    public void writeToGraphics(Graphics2D graphics2d) {
        /*graphics2d.setColor(Color.BLUE.brighter());
        graphics2d.setPaint(null);
        graphics2d.drawLine(20, 100, 120, 100);*/
        // TODO complet with working code.
    }
}
