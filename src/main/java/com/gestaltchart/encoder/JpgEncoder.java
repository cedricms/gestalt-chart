package com.gestaltchart.encoder;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import com.gestaltchart.chart.Chart;

public class JpgEncoder extends BitmapEncoder {

    BufferedImage chartImage;
    
    @Override
    public Graphics2D generateGraphics(Chart chart) {
        chartImage = new BufferedImage(chart.getCanvasWidth(), chart.getCanvasHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = chartImage.createGraphics();
        
        chart.writeToGraphics(graphics2D);
        
        return graphics2D;
    }
    
    @Override
    public void renderChart(Graphics2D graphics2D, File destination) throws Exception {
        renderBitMapChart(chartImage, "jpg", destination);
    }
}
