package com.gestaltchart.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.gestaltchart.data.DataPoint;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
public abstract class Chart {
    
    private int canvasWidth;
    private int canvasHeight;
    
    private String title;
    private Color titleColor = new Color(0.5f, 0.5f, 0.5f);
    private int titleSize = 18;
    
    private Color labelColor = new Color(0.6f, 0.6f, 0.6f);
    private int labelSize = 10;
    
    private List<DataPoint> data;
    
    private List<Color> dataPalette;
    
    private Color backgroundColor;
    
    public Chart(int canvasWidth, int canvasHeight) {
        super();
        
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        
        this.data = new ArrayList();
        
        this.dataPalette = new ArrayList();
        
        Color color1 = new Color(0.4f, 0.9f, 0.4f);
        this.dataPalette.add(color1);
        
        Color color2 = new Color(0.9f, 0.4f, 0.4f);
        this.dataPalette.add(color2);
        
        Color color3 = new Color(0.5f, 0.5f, 0.9f);
        this.dataPalette.add(color3);
        
        Color color4 = new Color(0.9f, 0.9f, 0.4f);
        this.dataPalette.add(color4);
        
        Color color5 = new Color(0.7f, 0.4f, 0.9f);
        this.dataPalette.add(color5);
        
        Color color6 = new Color(0.9f, 0.7f, 0.4f);
        this.dataPalette.add(color6);
        
        Color color7 = new Color(0.3f, 0.3f, 1.0f);
        this.dataPalette.add(color7);
        
        Color color8 = new Color(0.9f, 0.5f, 0.5f);
        this.dataPalette.add(color8);
        
        this.backgroundColor = new Color(1f, 1f, 1f);
    }
    
    public Chart(int canvasWidth, int canvasHeight, String title) {
        this(canvasWidth, canvasHeight);
        
        this.title = title;
    }
    
    public void writeToGraphics(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(3));
                
        writeToGraphicsChartData(graphics2D);
        
        writeTitle(graphics2D);
        
        // writeCenterAxis(graphics2D);
    }
    
    private void writeCenterAxis(Graphics2D graphics2D) {
        graphics2D.setColor(new Color(1.0f, 0.0f, 1.0f));
        
        int center = this.canvasWidth / 2;
        int middle = this.canvasHeight / 2;            
        
        // Vertical line going through the center.
        graphics2D.drawLine(center, 0, center, this.canvasHeight);
        
        // Horizontal line going through the middle.
        graphics2D.drawLine(0, middle, this.canvasWidth, middle);
    }
    
    protected void writeTitle(Graphics2D graphics2D) {
        log.debug("Title : " + this.title);
        if (this.title != null) {            
            graphics2D.setColor(this.titleColor);
            
            Font font = new Font("SansSerif", Font.BOLD, this.titleSize);
            graphics2D.setFont(font);
            
            FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
            
            int titleWidth = fontMetrics.stringWidth(title);
            int center = (this.canvasWidth - titleWidth) / 2;
            
            int titleHeight = fontMetrics.getHeight();
            
            graphics2D.drawString(this.title, center, titleHeight);
        }
    }
    
    public abstract void writeToGraphicsChartData(Graphics2D graphics2d);
}
