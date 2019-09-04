package com.gestaltchart.encoder;

import java.awt.Graphics2D;
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
        
        Graphics2D graphics2d = generateGraphics(chart);
        
        renderChart(graphics2d, destination);
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
}
