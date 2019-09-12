package com.gestaltchart.encoder;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import com.gestaltchart.chart.Chart;

public class PngEncoder extends AbstractEncoder {

    BufferedImage chartImage;
    
    @Override
    public Graphics2D generateGraphics(Chart chart) {
        chartImage = new BufferedImage(chart.getCanvasWidth(), chart.getCanvasHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = chartImage.createGraphics();
        
        chart.writeToGraphics(graphics2D);
        
        return graphics2D;
    }
    
    @Override
    public void renderChart(Graphics2D graphics2D, File destination) throws Exception {
        ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();
        
        ImageWriteParam param = writer.getDefaultWriteParam();

        if (param.canWriteCompressed()) { 
            // NOTE: Any method named [set|get]Compression.* throws UnsupportedOperationException if false
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(1.0f);
        }
        
        writer.setOutput(ImageIO.createImageOutputStream(destination));
        writer.write(chartImage);
    }
}
