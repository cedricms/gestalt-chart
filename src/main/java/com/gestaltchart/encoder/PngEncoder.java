package com.gestaltchart.encoder;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;

import com.gestaltchart.chart.Chart;

public class PngEncoder extends AbstractEncoder {

    BufferedImage image;
    
    @Override
    public Graphics2D generateGraphics(Chart chart) {
        image = new BufferedImage(chart.getCanvasWidth(), chart.getCanvasHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2d = image.createGraphics();
        
        chart.writeToGraphics(graphics2d);
        
        return graphics2d;
    }
    
    @Override
    public void renderChart(File destination) throws Exception {
        ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();
        writer.setOutput(ImageIO.createImageOutputStream(destination));
        writer.write(image);
    }
}
