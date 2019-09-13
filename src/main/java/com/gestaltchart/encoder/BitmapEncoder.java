package com.gestaltchart.encoder;

import java.awt.image.BufferedImage;

import java.io.File;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public abstract class BitmapEncoder extends AbstractEncoder {
    
    protected float COMPRESSION_QUALITY = 0.875f;
    
    public void renderBitMapChart(BufferedImage chartImage, String formatName, File destination) throws Exception {
        ImageWriter writer = ImageIO.getImageWritersByFormatName(formatName).next();
        ImageWriteParam writeParam = writer.getDefaultWriteParam();
        if (writeParam.canWriteCompressed()) { 
            writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            writeParam.setCompressionQuality(COMPRESSION_QUALITY);
        }

        ImageOutputStream outputStream = ImageIO.createImageOutputStream(destination);
        writer.setOutput(outputStream);
        IIOImage outputImage = new IIOImage(chartImage, null, null);
        writer.write(null, outputImage, writeParam);
        writer.dispose();
    }
}
