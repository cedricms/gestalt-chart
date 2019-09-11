package com.gestaltchart.chart.line;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.gestaltchart.chart.SeriesChart;
import com.gestaltchart.data.DataPoint;
import com.gestaltchart.data.LineDataPoint;
import com.gestaltchart.data.series.Series;

public class LineChart extends SeriesChart {

    public LineChart(int canvasWidth, int canvasHeight) {
        super(canvasWidth, canvasHeight);
    }
    
    public LineChart(int canvasWidth, int canvasHeight, String title) {
        super(canvasWidth, canvasHeight, title);
    }
    
    @Override
    public void writeToGraphicsChartData(Graphics2D graphics2D) {
        if (!CollectionUtils.isEmpty(this.getSeriesList())) {
            
            int xMin = 10;
            int xMax = this.getCanvasWidth() - xMin;
            
            int yMin = 0;
            if (this.getTitle() != null) {
                yMin = 20;
            }
            int yMax = this.getCanvasHeight() - xMin;
            
            int seriesColorIndex = 0;
            for (Series series : this.getSeriesList()) {
                List<DataPoint> data = series.getData();
                
                int quantityOfData = data.size();
                
                int xIncrement = (xMax - xMin) / quantityOfData;
                int yIncrement = (yMax - yMin) / quantityOfData;
                
                Float maxHeightValue = findMaxHeightValue(data);
                
                float scale = yMax / maxHeightValue;
                
                for (int i = 1 ; i < quantityOfData ; i++) {
                    graphics2D.setColor(this.getSeriesColor().get(seriesColorIndex));
                    
                    LineDataPoint dataPoint1 = (LineDataPoint) data.get(i - 1);
                    LineDataPoint dataPoint2 = (LineDataPoint) data.get(i);
                    
                    graphics2D.drawLine((xIncrement * (i - 1)) + xMin
                    , (int) ((dataPoint1.getOrdinate().floatValue() * scale) + xMin)
                    , (xIncrement * i) + xMin
                    , (int) ((dataPoint2.getOrdinate().floatValue() * scale) + xMin));
                }
                
                seriesColorIndex++;
            }
        }
    }
    
    private Float findMaxHeightValue(List<DataPoint> data) {
        Float result = new Float(0);
        
        for (DataPoint dataPoint : data) {
            LineDataPoint lineDataPoint = (LineDataPoint) dataPoint;
            Float height = lineDataPoint.getOrdinate();
            
            if ((height != null) && (height.floatValue() > result.floatValue())) {
                result = height;
            }
        }
        
        return result;
    }
}
