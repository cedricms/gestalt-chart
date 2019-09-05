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
    public void encodeGivenEmptyChartAndValideFileThenLiftException() throws Exception {
        // Given
        Chart chart = new LineChart(400, 300);
        File destination = new File(TestConstants.TEST_ROOT_DIRECTORY + File.separator + "encodeGivenEmptyAndValideFileThenLiftException.svg");
        SvgEncoder encoder = new SvgEncoder();
        
        // When
        encoder.encode(chart, destination);

        // Then
        assertThat(destination.exists()).isEqualTo(true);
    }
    
    @Test
    public void encodeGivenChartWithTitleAndValideFileThenLiftException() throws Exception {
        // Given
        String title = "Test Chart 2";
        Chart chart = new LineChart(400, 300, title);
        File destination = new File(TestConstants.TEST_ROOT_DIRECTORY + File.separator + "encodeGivenChartWithTitleAndValideFileThenLiftException.svg");
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
}
