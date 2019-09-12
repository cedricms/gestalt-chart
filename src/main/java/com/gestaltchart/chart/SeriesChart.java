package com.gestaltchart.chart;

import java.util.ArrayList;
import java.util.List;

import com.gestaltchart.data.series.Series;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
public abstract class SeriesChart extends Chart {
    
    private List<Series> seriesList;

    public SeriesChart(int canvasWidth, int canvasHeight) {
        super(canvasWidth, canvasHeight);
        
        this.seriesList = new ArrayList();
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
