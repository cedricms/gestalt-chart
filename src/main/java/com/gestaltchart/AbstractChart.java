package com.gestaltchart;

public abstract class AbstractChart implements Chart {
    
    private int width;
    private int height;
    
    private String title;
    
    public AbstractChart(int width, int height) {
        super();
        
        this.width = width;
        this.height = height;
    }
    
    public AbstractChart(int width, int height, String title) {
        this(width, height);
        
        this.title = title;
    }
}
