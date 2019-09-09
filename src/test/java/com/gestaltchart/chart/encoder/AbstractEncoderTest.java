package com.gestaltchart.chart.encoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.log4j.Log4j2;

import org.apache.commons.io.FileUtils;
import org.junit.Before;

import static org.assertj.core.api.Assertions.*;

import com.gestaltchart.TestConstants;
import com.gestaltchart.data.DataPoint;
import com.gestaltchart.data.LineDataPoint;
import com.gestaltchart.data.series.LineSeries;

@Log4j2
public abstract class AbstractEncoderTest {

    protected File testRootDirectoryFile;

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
        
    protected void assertExceptionIsThrown(Throwable thrown, Class exceptionClass) {
        assertThat(thrown).isInstanceOf(exceptionClass);
    }
    
    protected boolean doesWordOccureInFile(File file, String wordToFind) {
        boolean result = false;
        try(FileReader fileReader = new FileReader(file); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
          while((line = bufferedReader.readLine()) != null) {
              log.debug("Line : " + line);
              if (line.contains(wordToFind)) {
                 result = true;
                 break;
              }
          }
      } catch (IOException ioe) {
          log.error("Unable to read file : " + file.getAbsolutePath(), ioe);
      }
      
      return result;
   }
   
   protected LineSeries generateLineSeries(String label, int numberOfDataPoints, int maxValue) {
       List<DataPoint> data = new ArrayList();
       for (int i = 0; i < numberOfDataPoints; i++) {
           LineDataPoint dataPoint = new LineDataPoint();
           dataPoint.setAbscissa(new Float(i));
           dataPoint.setOrdinate(new Float(Math.random() * maxValue));
           
           data.add(dataPoint);
       }
       
       LineSeries lineSeries = new LineSeries(label, data);
       
       return lineSeries;
   }
}