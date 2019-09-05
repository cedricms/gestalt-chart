package com.gestaltchart.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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
    
    private List<Series> seriesList;
    
    private Color titleColor = new Color(0.1f, 0.1f, 0.1f);
    
    public Chart(int canvasWidth, int canvasHeight) {
        super();
        
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        
        this.seriesList = new ArrayList<>();
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
        
        log.debug("Title : " + this.title);
        if (this.title != null) {            
            graphics2D.setColor(titleColor);
            Font font = new Font("SansSerif", Font.BOLD, 36);
            graphics2D.setFont(font);
            int center = this.canvasWidth / 2;
            graphics2D.drawString(this.title, center, 50);
            
            graphics2D.setColor(titleColor);
            graphics2D.drawLine(center - 20, 60, center + 20, 60);
        }
        
        writeToGraphicsChartData(graphics2D);
    }
    
    public abstract void writeToGraphicsChartData(Graphics2D graphics2d);
}
