package com.gestaltchart.encoder;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileWriter;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.svg.SVGDocument;

import com.gestaltchart.chart.Chart;

/**
 * cf. https://xmlgraphics.apache.org/batik/using/svg-generator.html
 */
public class SvgEncoder extends AbstractEncoder {

    @Override
    public Graphics2D generateGraphics(Chart chart) {
        // Create an SVG document.
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        SVGDocument doc = (SVGDocument) impl.createDocument(svgNS, "svg", null);
    
        // Create a converter for this document.
        SVGGraphics2D graphics2D = new SVGGraphics2D(doc);
        
        return graphics2D;
    }
    
    @Override
    public void renderChart(Graphics2D graphics2D, File destination) throws Exception {
        SVGGraphics2D svgGraphics2D = (SVGGraphics2D) graphics2D;
        svgGraphics2D.stream(new FileWriter(destination));
        svgGraphics2D.dispose();
    }
}
