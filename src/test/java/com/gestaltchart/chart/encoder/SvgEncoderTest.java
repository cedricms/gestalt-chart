package com.gestaltchart.chart.encoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import lombok.extern.log4j.Log4j2;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

import com.gestaltchart.TestConstants;
import com.gestaltchart.chart.Chart;
import com.gestaltchart.chart.ChartNotFoundException;
import com.gestaltchart.chart.line.LineChart;
import com.gestaltchart.data.series.LineSeries;
import com.gestaltchart.encoder.SvgEncoder;

@Log4j2
public class SvgEncoderTest extends AbstractEncoderTest {
    
    @Test
    public void encodeGivenNullAndNullThenLiftException() throws Exception {
        // Given
        Chart chart = null;
        File destination = null;
        SvgEncoder encoder = new SvgEncoder();
        
        // When
        Throwable thrown = catchThrowable(() -> {
            encoder.encode(chart, destination);
        });

        // Then
        assertExceptionIsThrown(thrown, ChartNotFoundException.class);
    }
    
    @Test
    public void encodeGivenEmptyChartAndNullThenLiftException() throws Exception {
        // Given
        Chart chart = new LineChart(400, 300);
        File destination = null;
        SvgEncoder encoder = new SvgEncoder();
        
        // When
        Throwable thrown = catchThrowable(() -> {
            encoder.encode(chart, destination);
        });

        // Then
        assertExceptionIsThrown(thrown, FileNotFoundException.class);
    }
    
    @Test
    public void encodeGivenEmptyChartAndValideFileThenGenerateFile() throws Exception {
        // Given
        Chart chart = new LineChart(400, 300);
        File destination = new File(TestConstants.TEST_ROOT_DIRECTORY + File.separator + "encodeGivenEmptyChartAndValideFileThenGenerateFile.svg");
        SvgEncoder encoder = new SvgEncoder();
        
        // When
        encoder.encode(chart, destination);

        // Then
        assertThat(destination.exists()).isEqualTo(true);
    }
    
    @Test
    public void encodeGivenChartWithTitleAndValideFileThenGenerateFile() throws Exception {
        // Given
        String title = "Test Chart 2";
        Chart chart = new LineChart(400, 300, title);
        File destination = new File(TestConstants.TEST_ROOT_DIRECTORY + File.separator + "encodeGivenChartWithTitleAndValideFileThenGenerateFile.svg");
        SvgEncoder encoder = new SvgEncoder();
        
        // When
        encoder.encode(chart, destination);

        // Then
        assertThat(destination.exists()).isEqualTo(true);
        long fileSize = destination.length();
        assertThat(fileSize).isGreaterThan(0);
        log.info("File size : " + fileSize);
        assertThat(doesWordOccureInFile(destination, title)).isEqualTo(true);
    }
    
    @Test
    public void encodeGivenChartWithOutTitleWithSeriesAndValideFileThengenerateFile() throws Exception {
        // Given
        Chart chart = new LineChart(400, 300);
        
        LineSeries lineSeries = generateLineSeries("Series Label", 10, 2000);
        chart.addSeries(lineSeries);
        
        File destination = new File(TestConstants.TEST_ROOT_DIRECTORY + File.separator + "encodeGivenChartWithOutTitleWithSeriesAndValideFileThengenerateFile.svg");
        SvgEncoder encoder = new SvgEncoder();
        
        // When
        encoder.encode(chart, destination);

        // Then
        assertThat(destination.exists()).isEqualTo(true);
        long fileSize = destination.length();
        assertThat(fileSize).isGreaterThan(0);
        log.info("File size : " + fileSize);
    }
    
    
    @Test
    public void encodeGivenChartWithTitleWithMultipleSeriesAndValideFileThengenerateFile() throws Exception {
        // Given
        String title = "Test Chart 3";
        Chart chart = new LineChart(400, 300, title);
        
        LineSeries lineSeries1 = generateLineSeries("Series Label 1", 10, 2000);
        chart.addSeries(lineSeries1);
        
        LineSeries lineSeries2 = generateLineSeries("Series Label 2", 10, 3000);
        chart.addSeries(lineSeries2);
        
        LineSeries lineSeries3 = generateLineSeries("Series Label 3", 10, 4000);
        chart.addSeries(lineSeries3);
        
        LineSeries lineSeries4 = generateLineSeries("Series Label 4", 10, 5000);
        chart.addSeries(lineSeries4);
        
        File destination = new File(TestConstants.TEST_ROOT_DIRECTORY + File.separator + "encodeGivenChartWithTitleWithMultipleSeriesAndValideFileThengenerateFile.svg");
        SvgEncoder encoder = new SvgEncoder();
        
        // When
        encoder.encode(chart, destination);

        // Then
        assertThat(destination.exists()).isEqualTo(true);
        long fileSize = destination.length();
        assertThat(fileSize).isGreaterThan(0);
        log.info("File size : " + fileSize);
    }
}
