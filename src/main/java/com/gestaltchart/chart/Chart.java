package com.gestaltchart.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.gestaltchart.data.series.Series;

import lombok.Data;

@Data
public abstract class Chart {
    
    private int canvasWidth;
    private int canvasHeight;
    
    private String title;
    
    private List<Series> seriesList;
    
    private Color titleColor = Color.DARK_GRAY;
    
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
    
    public void writeToGraphics(Graphics2D graphics2d) {
        if (this.title != null) {
            graphics2d.setColor(titleColor);
            Font font = new Font("SansSerif", Font.BOLD, 36);
            graphics2d.setFont(font);
            graphics2d.drawString(this.title, this.canvasWidth / 2, 10);
        }
    }
}
