package com.gestaltchart.data.series;

import java.util.List;

import lombok.Data;

import com.gestaltchart.data.DataPoint;

@Data
public class LineSeries extends AbstractSeries {
    
    boolean areaVisible = false;
    
    boolean interpolationVisible = false;
    
    boolean pointsVisible = true;
    
    public LineSeries(String label, List<DataPoint> data) {
        super(label, data);
    }

}
