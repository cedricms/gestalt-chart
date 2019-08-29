package com.gestaltchart.series;

import java.awt.geom.Point2D;

import lombok.Data;

@Data
public class AbstractSeries implements Series {
    
    private String label;
    private Point2D[] data;
    
    public AbstractSeries(String label, Point2D[] data) {
        super();
        
        this.label = label;
        this.data = data;
    }
}
