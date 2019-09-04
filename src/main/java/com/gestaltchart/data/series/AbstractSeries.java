package com.gestaltchart.data.series;

import java.util.List;

import com.gestaltchart.data.DataPoint;

import lombok.Data;

@Data
public class AbstractSeries implements Series {
    
    private String label;
    private List<DataPoint> data;
    
    public AbstractSeries(String label, List<DataPoint> data) {
        super();
        
        this.label = label;
        this.data = data;
    }
}
