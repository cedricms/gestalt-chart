package com.gestaltchart.chart;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

import com.gestaltchart.data.series.Series;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
public abstract class SeriesChart extends Chart {
    
    private List<Series> seriesList;

    private List<Color> seriesColor;
    
    public SeriesChart(int canvasWidth, int canvasHeight) {
        super(canvasWidth, canvasHeight);
        
        this.seriesList = new ArrayList();
        
        this.seriesColor = new ArrayList();
        
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
    
    public SeriesChart(int canvasWidth, int canvasHeight, String title) {
        this(canvasWidth, canvasHeight);
        
        this.setTitle(title);
    }
    
    public void addSeries(Series series) {
        if (this.seriesList == null) {
            this.seriesList = new ArrayList();
        }
        
        this.seriesList.add(series);
    }
}
