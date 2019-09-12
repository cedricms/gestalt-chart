package com.gestaltchart.encoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.log4j.Log4j2;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

import com.gestaltchart.TestConstants;
import com.gestaltchart.chart.Chart;
import com.gestaltchart.chart.ChartNotFoundException;
import com.gestaltchart.chart.line.LineChart;
import com.gestaltchart.chart.pie.DoughnutChart;
import com.gestaltchart.data.DataPoint;
import com.gestaltchart.encoder.PngEncoder;

@Log4j2
public class PngEncoderTest extends AbstractEncoderTest {
    
    @Test
    public void encodeGivenNullAndNullThenLiftException() throws Exception {
        // Given
        Chart chart = null;
        File destination = null;
        PngEncoder pngEncoder = new PngEncoder();
        
        // When
        Throwable thrown = catchThrowable(() -> {
            pngEncoder.encode(chart, destination);
        });

        // Then
        assertExceptionIsThrown(thrown, ChartNotFoundException.class);
    }
    
    @Test
    public void encodeGivenEmptyChartAndNullThenLiftException() throws Exception {
        // Given
        Chart chart = new LineChart(400, 300);
        File destination = null;
        PngEncoder pngEncoder = new PngEncoder();
        
        // When
        Throwable thrown = catchThrowable(() -> {
            pngEncoder.encode(chart, destination);
        });

        // Then
        assertExceptionIsThrown(thrown, FileNotFoundException.class);
    }
    
    @Test
    public void encodeGivenEmptyChartAndValideFileThenGenerateFile() throws Exception {
        // Given
        Chart chart = new LineChart(400, 300);
        File destination = new File(TestConstants.TEST_ROOT_DIRECTORY + File.separator + "encodeGivenEmptyChartAndValideFileThenGenerateFile.png");
        PngEncoder pngEncoder = new PngEncoder();
        
        // When
        pngEncoder.encode(chart, destination);

        // Then
        assertThat(destination.exists()).isEqualTo(true);
    }
    
    @Test
    public void encodeGivenChartWithTitleAndValideFileThenGenerateFile() throws Exception {
        // Given
        Chart chart = new LineChart(400, 300, "Test Chart 1");
        File destination = new File(TestConstants.TEST_ROOT_DIRECTORY + File.separator + "encodeGivenChartWithTitleAndValideFileThenGenerateFile.png");
        PngEncoder pngEncoder = new PngEncoder();
        
        // When
        pngEncoder.encode(chart, destination);

        // Then
        assertThat(destination.exists()).isEqualTo(true);
        long fileSize = destination.length();
        assertThat(fileSize).isGreaterThan(0);
        log.info("File size : " + fileSize);
    }
    
        @Test
    public void encodeGivenDoughnutChartThenGenerateFile() throws Exception {
        // Given
        String title = "Test Chart 2";
        Chart chart = new DoughnutChart(500, 400, title);
        
        List<DataPoint> data = new ArrayList();
        
        DataPoint data1 = createDoughnutDataPoint("Data 1", 42);
        data.add(data1);
        
        DataPoint data2 = createDoughnutDataPoint("Data 2", 3.14159);
        data.add(data2);
        
        DataPoint data3 = createDoughnutDataPoint("Data 3", 51);
        data.add(data3);
        
        DataPoint data4 = createDoughnutDataPoint("Data 4", 3);
        data.add(data4);
        
        DataPoint data5 = createDoughnutDataPoint("Data 5", 7);
        data.add(data5);
        
        DataPoint data6 = createDoughnutDataPoint("Data 6", 2.1);
        data.add(data6);
        
        DataPoint data7 = createDoughnutDataPoint("Data 7", 10);
        data.add(data7);
        
        DataPoint data8 = createDoughnutDataPoint("Data 8", 38);
        data.add(data8);
        
        chart.setData(data);
        
        File destination = new File(TestConstants.TEST_ROOT_DIRECTORY + File.separator + "encodeGivenDoughnutChartThenGenerateFile.png");
        PngEncoder encoder = new PngEncoder();
        
        // When
        encoder.encode(chart, destination);

        // Then
        assertThat(destination.exists()).isEqualTo(true);
        long fileSize = destination.length();
        assertThat(fileSize).isGreaterThan(0);
        log.info("File size : " + fileSize);
    }
}
