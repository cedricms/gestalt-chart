package com.gestaltchart.chart;

import java.util.ArrayList;
import java.util.List;

import com.gestaltchart.data.series.Series;

import lombok.Data;

@Data
public abstract class AbstractChart implements Chart {
    
    private int canvasWidth;
    private int canvasHeight;
    
    private String title;
    
    private List<Series> seriesList;
    
    public AbstractChart(int canvasWidth, int canvasHeight) {
        super();
        
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        
        this.seriesList = new ArrayList<>();
    }
    
    public AbstractChart(int canvasWidth, int canvasHeight, String title) {
        this(canvasWidth, canvasHeight);
        
        this.title = title;
    }
    
    public void addSeries(Series series) {
        if (this.seriesList == null) {
            this.seriesList = new ArrayList();
        }
        
        this.seriesList.add(series);
    }
}
