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
    
    private List<DataPoint> data;
    
    public Chart(int canvasWidth, int canvasHeight) {
        super();
        
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        
        this.data = new ArrayList();
    }
    
    public Chart(int canvasWidth, int canvasHeight, String title) {
        this(canvasWidth, canvasHeight);
        
        this.title = title;
    }
    
    public void writeToGraphics(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(3));
        
        writeTitle(graphics2D);
        
        writeToGraphicsChartData(graphics2D);
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
