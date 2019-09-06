package com.gestaltchart.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.gestaltchart.data.series.Series;

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
    
    private List<Series> seriesList;

    private List<Color> seriesColor;
    
    public Chart(int canvasWidth, int canvasHeight) {
        super();
        
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        
        this.seriesList = new ArrayList<>();
        
        this.seriesColor = new ArrayList<>();
        
        Color color1 = new Color(0.4f, 0.9f, 0.4f);
        this.seriesColor.add(color1);
        
        Color color2 = new Color(0.9f, 0.4f, 0.4f);
        this.seriesColor.add(color2);
        
        Color color3 = new Color(0.4f, 0.4f, 0.9f);
        this.seriesColor.add(color3);
        
        Color color4 = new Color(0.9f, 0.9f, 0.4f);
        this.seriesColor.add(color4);
        
        Color color5 = new Color(0.9f, 0.4f, 0.9f);
        this.seriesColor.add(color5);
        
        Color color6 = new Color(0.4f, 0.9f, 0.9f);
        this.seriesColor.add(color6);
    }
    
    public Chart(int canvasWidth, int canvasHeight, String title) {
        this(canvasWidth, canvasHeight);
        
        this.title = title;
    }
    
    public void addSeries(Series series) {
        if (this.seriesList == null) {
            this.seriesList = new ArrayList();
        }
        
        this.seriesList.add(series);
    }
    
    public void writeToGraphics(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(3));
        
        writeTitle(graphics2D);
        
        writeToGraphicsChartData(graphics2D);
    }
    
    private void writeTitle(Graphics2D graphics2D) {
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
            
            /*graphics2D.setColor(titleColor);
            graphics2D.drawLine(center - 20, 60, center + 20, 60);*/
        }
    }

    
    public abstract void writeToGraphicsChartData(Graphics2D graphics2d);
}
