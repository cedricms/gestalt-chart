package com.gestaltchart.chart.pie;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.NoSuchElementException;

import lombok.extern.log4j.Log4j2;

import org.apache.commons.collections4.CollectionUtils;

import com.gestaltchart.chart.Chart;
import com.gestaltchart.data.DataPoint;
import com.gestaltchart.data.LineDataPoint;
import com.gestaltchart.data.series.Series;

@Log4j2
public class DoughnutChart extends Chart {

    public DoughnutChart(int canvasWidth, int canvasHeight) {
        super(canvasWidth, canvasHeight);
    }
    
    public DoughnutChart(int canvasWidth, int canvasHeight, String title) {
        super(canvasWidth, canvasHeight, title);
    }
    
    protected void writeTitle(Graphics2D graphics2D) {
        log.debug("Title : " + this.getTitle());
        if (this.getTitle() != null) {            
            graphics2D.setColor(this.getTitleColor());
            
            Font font = new Font("SansSerif", Font.BOLD, this.getTitleSize());
            graphics2D.setFont(font);
            
            FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
            
            int titleWidth = fontMetrics.stringWidth(this.getTitle());
            int center = (this.getCanvasWidth() - titleWidth) / 2;
            
            int titleHeight = fontMetrics.getHeight();
            int middle = (this.getCanvasHeight() - titleHeight) / 2;
                   
            graphics2D.drawString(this.getTitle(), center, middle);
        }
    }
    
    @Override
    public void writeToGraphicsChartData(Graphics2D graphics2D) {
        if (!CollectionUtils.isEmpty(this.getData())) {
            double maxValue = findDataMaxValue();
            
            log.debug("Doughnut chart maxValue = " + maxValue);
        }
    }
    
    private double findDataMaxValue() {
        double result = 0;
        
        result = this.getData().stream()
                  .mapToDouble(dataPoint -> dataPoint.getValue())
                  .max().orElseThrow(NoSuchElementException::new);
        
        return result;
    }
}
