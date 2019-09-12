package com.gestaltchart.chart;

import java.awt.Color;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

import com.gestaltchart.chart.line.LineChart;

public class ChartTest {
    
    @Test
    public void getDataPaletteColorGiven0ThenColor0() {
        // Given
        LineChart lineChart = new LineChart(320, 480);
        int id = 0;
        
        // When
        Color color = lineChart.getDataPaletteColor(id);
        
        // Then
        assertThat(color).isNotNull();
        assertThat(color.toString()).isEqualTo(lineChart.getDataPalette().get(id).toString());
    }
    
    
    @Test
    public void getDataPaletteColorGiven3ThenColor3() {
        // Given
        LineChart lineChart = new LineChart(320, 480);
        int id = 3;
        
        // When
        Color color = lineChart.getDataPaletteColor(id);
        
        // Then
        assertThat(color).isNotNull();
        assertThat(color.toString()).isEqualTo(lineChart.getDataPalette().get(id).toString());
    }
    
    @Test
    public void getDataPaletteColorGiven9ThenColor1() {
        // Given
        LineChart lineChart = new LineChart(320, 480);
        int id = 9;
        
        // When
        Color color = lineChart.getDataPaletteColor(id);
        
        // Then
        assertThat(color).isNotNull();
        assertThat(color.toString()).isEqualTo(lineChart.getDataPalette().get(1).toString());
    }
    
    @Test
    public void getDataPaletteColorGiven13ThenColor5() {
        // Given
        LineChart lineChart = new LineChart(320, 480);
        int id = 13;
        
        // When
        Color color = lineChart.getDataPaletteColor(id);
        
        // Then
        assertThat(color).isNotNull();
        assertThat(color.toString()).isEqualTo(lineChart.getDataPalette().get(5).toString());
    }
}