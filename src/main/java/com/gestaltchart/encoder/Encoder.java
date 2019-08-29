package com.gestaltchart.encoder;

import java.io.File;

import com.gestaltchart.Chart;

public interface Encoder {
    
    void encode(Chart chart, File destination);
}
