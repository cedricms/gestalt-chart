package com.gestaltchart.encoder;

import java.awt.Graphics2D;
import java.io.File;

import com.gestaltchart.chart.Chart;

public interface Encoder {
    
    void encode(Chart chart, File destination) throws Exception;
    
    Graphics2D generateGraphics(Chart chart);
    
    void renderChart(Chart chart, File destination);
}
