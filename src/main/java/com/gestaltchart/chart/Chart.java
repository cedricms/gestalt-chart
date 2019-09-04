package com.gestaltchart.chart;

import java.awt.Graphics2D;

import com.gestaltchart.data.series.Series;

public interface Chart {
    
    void addSeries(Series series);
    
    void writeToGraphics(Graphics2D graphics2d);
}
