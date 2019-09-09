package com.gestaltchart.encoder;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.gestaltchart.chart.Chart;
import com.gestaltchart.chart.ChartNotFoundException;

public abstract class AbstractEncoder implements Encoder {
    
    @Override
    public void encode(Chart chart, File destination) throws Exception {
        validateEncodingInputs(chart, destination);
        
        createFile(destination);
        
        Graphics2D graphics2D = generateGraphics(chart);
        
        intitializeRenderHints(graphics2D);
        
        renderChart(graphics2D, destination);
    }
    
    protected void validateEncodingInputs(Chart chart, File destination) throws Exception {
        if (chart == null) {
            throw new ChartNotFoundException();
        }
        
        if (destination == null) {
            throw new FileNotFoundException("The destination file must not be set to null.");
        }
    }
    
    protected void createFile(File destination) throws IOException {
        if (!destination.exists()) {
            destination.createNewFile();
        }
    }
    
    protected void intitializeRenderHints(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
			RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
    	graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    			RenderingHints.VALUE_ANTIALIAS_DEFAULT);
    }
}
