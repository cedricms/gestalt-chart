package com.gestaltchart.chart.pie;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.NoSuchElementException;

import lombok.extern.log4j.Log4j2;

import org.apache.commons.collections4.CollectionUtils;

import com.gestaltchart.chart.Chart;
import com.gestaltchart.data.DataPoint;
import com.gestaltchart.data.DoughnutDataPoint;
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
        cutOutDoughnut(graphics2D);
        
        log.debug("Title : " + this.getTitle());
        if (this.getTitle() != null) {            
            graphics2D.setColor(this.getTitleColor());
            
            Font font = new Font("SansSerif", Font.BOLD, this.getTitleSize());
            graphics2D.setFont(font);
            
            FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
            
            int titleWidth = fontMetrics.stringWidth(this.getTitle());
            int center = (int) ((this.getCanvasWidth() - (titleWidth * 0.9)) / 2);
            
            int titleHeight = fontMetrics.getHeight();
            int middle = (this.getCanvasHeight() + (titleHeight / 2)) / 2;
                   
            graphics2D.drawString(this.getTitle(), center, middle);
        }
    }
    
    public void cutOutDoughnut(Graphics2D graphics2D) {
        graphics2D.setColor(this.getBackgroundColor());
        
        Font font = new Font("SansSerif", Font.PLAIN, this.getTitleSize());
        graphics2D.setFont(font);
        
        FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
        
        double titleLength = (this.getTitle() == null) ? (this.getCanvasWidth() * 0.4) : fontMetrics.stringWidth(this.getTitle());
        
        double radius = titleLength * 1.3;
        
        double w = radius;
        double h = radius;
        double x = (this.getCanvasWidth() - w) / 2;
        double y = (this.getCanvasHeight() - h) / 2;
        
		Shape circle = new Ellipse2D.Double(x, y, w, h);
		graphics2D.fill(circle);
    }
    
    @Override
    public void writeToGraphicsChartData(Graphics2D graphics2D) {
        if (!CollectionUtils.isEmpty(this.getData())) {
            double totalValue = findDataTotalValue();
            
            int center = this.getCanvasWidth() / 2;
            int middle = this.getCanvasHeight() / 2;
            log.debug("Center of canvas = " + center);
            log.debug("Middle of canvas = " + middle);
            
            double radius = ((center < middle) ? center : middle) * 0.8;
            log.debug("Radius = " + radius);
            
            int dataPointId = 0;
            double currentAngle = 0;
            for (DataPoint dataPoint : this.getData()) {
                log.debug("DataPoint value = " + dataPoint.getValue()); 
                
                double angle = 360 * dataPoint.getValue() / totalValue;
                log.debug("DataPoint angle = " + angle);                 
                
                drawArc(graphics2D, dataPoint, radius, currentAngle, angle, dataPointId);    
                
                drawLabel(graphics2D, dataPoint, center, middle, radius, angle, currentAngle);
            
                currentAngle = currentAngle + angle;
                log.debug("Current angle = " + currentAngle);
                
                dataPointId++;
            }
            
            currentAngle = 0;
            for (DataPoint dataPoint : this.getData()) {
                double angle = 360 * dataPoint.getValue() / totalValue;
                
                drawValue(graphics2D, dataPoint, center, middle, radius, angle, currentAngle);
                
                currentAngle = currentAngle + angle;
            }
            
            log.debug("Doughnut chart totalValue = " + totalValue);
        }
    }
    
    private void drawValue(Graphics2D graphics2D
            , DataPoint dataPoint
            , int center
            , int middle
            , double radius
            , double angle
            , double currentAngle) {
        NumberFormat numberFormat = DecimalFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        
        String value = numberFormat.format(dataPoint.getValue());
                
        Font font = new Font("SansSerif", Font.BOLD, this.getLabelSize());
        graphics2D.setFont(font);
        
        FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
        
        double radiusFactor = 0.75;
        
        double valueAngle = currentAngle + (angle / 2);
        
        int valueWidth = fontMetrics.stringWidth(value);
        int xValue = (int) ((center + (radius * radiusFactor * Math.cos(Math.toRadians(valueAngle))) - (valueWidth / 2)));
        
        int valueHeight = fontMetrics.getHeight();
        int yValue = (int) ((middle - (radius * radiusFactor * Math.sin(Math.toRadians(valueAngle))) + ((valueHeight) / 2)));
        
        // Shadow
        graphics2D.setColor(this.getLabelColor().darker().darker());
        graphics2D.drawString(value, xValue + 1, yValue + 1);
        
        // Label
        graphics2D.setColor(this.getLabelColor().brighter().brighter());
        graphics2D.drawString(value, xValue, yValue);
    }
    
    private void drawLabel(Graphics2D graphics2D
            , DataPoint dataPoint
            , int center
            , int middle
            , double radius
            , double angle
            , double currentAngle) {
        String label = dataPoint.getLabel();
                
        Font font = new Font("SansSerif", Font.BOLD, this.getLabelSize());
        graphics2D.setFont(font);
        
        FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
        
        double labelAngle = currentAngle + (angle / 2);
        
        double radiusFactor = 1.15;
        
        int labelWidth = fontMetrics.stringWidth(label);
        int xLabel = (int) ((center + (radius * radiusFactor * Math.cos(Math.toRadians(labelAngle)) - (labelWidth / 2))));
        
        int labelHeight = fontMetrics.getHeight();
        int yLabel = (int) ((middle - (radius * radiusFactor * Math.sin(Math.toRadians(labelAngle)))));
        
        graphics2D.setColor(this.getLabelColor());
        graphics2D.drawString(label, xLabel, yLabel);
    }
    
    private void drawArc(Graphics2D graphics2D
            , DataPoint dataPoint
            , double radius
            , double currentAngle
            , double angle
            , int dataPointId) {
        graphics2D.setColor(this.getDataPalette().get(dataPointId));
        
        graphics2D.fillArc((int) ((this.getCanvasWidth() - (2 * radius)) / 2)
                        , (int) ((this.getCanvasHeight() - (2 * radius)) / 2)
                        , (int) (2 * radius)
                        , (int) (2 * radius)
                        , (int) currentAngle
                        , (int) angle);
    }
    
    private double findDataTotalValue() {
        double result = 0;
        
        result = this.getData().stream()
                  .mapToDouble(dataPoint -> dataPoint.getValue())
                  .sum();
        
        return result;
    }
}
