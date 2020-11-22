package ru.smak.gui.graphics;

import java.awt.*;

public class ColorScheme1 implements Colorizer{
    public Color getColor(float x){
        float r,g,b;
        r = (float)Math.abs(Math.sin(50-10*x)*Math.cos(-x));
        //g = (float)Math.abs(Math.sin(0.01*x)*Math.cos(x));
        g = (float)Math.abs(Math.sin(5*x)*Math.cos(x));
        //b = (float)Math.abs(1/(1000+Math.sin(x)*Math.cos(-x)));
       // b = (float)Math.abs(x*x);
        b = (float)Math.abs(Math.sin(x)*Math.cos(-x));
        return new Color(r,g,b);
    }
}
