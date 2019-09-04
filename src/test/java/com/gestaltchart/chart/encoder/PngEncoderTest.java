package com.gestaltchart.chart.encoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

import com.gestaltchart.TestConstants;
import com.gestaltchart.chart.Chart;
import com.gestaltchart.chart.ChartNotFoundException;
import com.gestaltchart.chart.line.LineChart;
import com.gestaltchart.encoder.PngEncoder;

public class PngEncoderTest {
    
    private File testRootDirectoryFile;

    @Before
    public void setUp() throws IOException {
        if (testRootDirectoryFile == null) {
            testRootDirectoryFile = new File(TestConstants.TEST_ROOT_DIRECTORY);
        }
        
        try {
            if (testRootDirectoryFile.exists()) {
                FileUtils.deleteDirectory(testRootDirectoryFile);
            }
        } catch (IOException ioe) {
            throw new IOException("Unable to delete : " + testRootDirectoryFile);
        }
        
        if (!testRootDirectoryFile.exists()) {
            testRootDirectoryFile.mkdir();
        }
    }
    
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
    
    private void assertExceptionIsThrown(Throwable thrown, Class exceptionClass) {
        assertThat(thrown).isInstanceOf(exceptionClass);
    }
    
    @Test
    public void encodeGivenEmptyAndNullThenLiftException() throws Exception {
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
    public void encodeGivenEmptyAndValideFileThenLiftException() throws Exception {
        // Given
        Chart chart = new LineChart(400, 300);
        File destination = new File(TestConstants.TEST_ROOT_DIRECTORY + File.separator + "encodeGivenEmptyAndValideFileThenLiftException.png");
        PngEncoder pngEncoder = new PngEncoder();
        
        // When
        pngEncoder.encode(chart, destination);

        // Then
        assertThat(destination.exists()).isEqualTo(true);
    }
}
