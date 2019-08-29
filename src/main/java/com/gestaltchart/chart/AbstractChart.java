package com.gestaltchart.chart;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public abstract class AbstractChart implements Chart {
    
    private int width;
    private int height;
    
    private String title;
    
    private List<Series> seriesList;
    
    public AbstractChart(int width, int height) {
        super();
        
        this.width = width;
        this.height = height;
        
        this.seriesList = new ArrayList<>();
    }
    
    public AbstractChart(int width, int height, String title) {
        this(width, height);
        
        this.title = title;
    }
    
    public void addSeries(Series series) {
        if (this.seriesList == null) {
            this.seriesList = new ArrayList();
        }
        
        this.seriesList.add(series);
    }
}
