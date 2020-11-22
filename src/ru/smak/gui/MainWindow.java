package ru.smak.gui;

import ru.smak.gui.graphics.ColorScheme1;
import ru.smak.gui.graphics.FractalPainter;
import ru.smak.gui.graphics.SelectionPainter;
import ru.smak.gui.graphics.components.GraphicsPanel;
import ru.smak.gui.graphics.coordinates.CartesianScreenPlane;
import ru.smak.gui.graphics.coordinates.Converter;
import ru.smak.math.Mandelbrot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    GraphicsPanel mainPanel;

    static final Dimension MIN_SIZE = new Dimension(450, 350);
    static final Dimension MIN_FRAME_SIZE = new Dimension(600, 500);

    public MainWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(MIN_FRAME_SIZE);
        setTitle("Фракталы");

        mainPanel = new GraphicsPanel();

        mainPanel.setBackground(Color.WHITE);

        GroupLayout gl = new GroupLayout(getContentPane());
        setLayout(gl);
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addComponent(mainPanel, (int)(MIN_SIZE.height*0.8), MIN_SIZE.height, GroupLayout.DEFAULT_SIZE)
                .addGap(4)
        );
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addGroup(gl.createParallelGroup()
                        .addComponent(mainPanel, MIN_SIZE.width, MIN_SIZE.width, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(4)
        );
        pack();
        var plane = new CartesianScreenPlane(
                mainPanel.getWidth(),
                mainPanel.getHeight(),
                -2, 1, -1, 1
        );

        var m = new Mandelbrot();
        var c = new ColorScheme1();

        var fp = new FractalPainter(plane, m);
        fp.col = c;
        var sp = new SelectionPainter(mainPanel.getGraphics());

        mainPanel.addPainter(fp);

        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                plane.setWidth(mainPanel.getWidth());
                plane.setHeight(mainPanel.getHeight());
                sp.setGraphics(mainPanel.getGraphics());
            }
        });
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                sp.setStartPoint(e.getPoint());
                sp.setVisible(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
               // sp.setVisible(false);

                var newx1 = Converter.xScr2Crt(sp.getStartPoint().x,plane);
                var newy1 = Converter.yScr2Crt(sp.getStartPoint().y,plane);
                var newx2 = Converter.xScr2Crt(e.getX(),plane);
                var newy2 = Converter.yScr2Crt(e.getY(),plane);
                if(newx1>newx2){
                    plane.xMin = newx2;
                    plane.xMax = newx1;
                }else{
                    plane.xMin = newx1;
                    plane.xMax = newx2;
                }
                if(newy1>newy2){
                    plane.yMin = newy2;
                    plane.yMax = newy1;
                }else{
                    plane.yMin = newy1;
                    plane.yMax = newy2;
                }

                mainPanel.repaint();
            }
        });

        mainPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                sp.setCurrentPoint(e.getPoint());
            }
        });
    }
}
