package ru.smak.gui.graphics;

import ru.smak.gui.graphics.coordinates.CartesianScreenPlane;
import ru.smak.gui.graphics.coordinates.Converter;
import ru.smak.math.Complex;
import ru.smak.math.Fractal;

import java.awt.*;

/**
 * Класс для отрисовки фрактала. Расширяет {@link Painter}
 */
public class FractalPainter extends Painter {

    //Поле данных о декартовой плоскости
    private final CartesianScreenPlane plane;
    public Colorizer col;
    //Поле интересующего фрактала
    private final Fractal fractal;

    /**
     * Конструктор класса
     * @param plane  информация о декартовой плоскости
     * @param f  отрисовываемый фрактал
     */
    public FractalPainter(CartesianScreenPlane plane,
                          Fractal f){
        this.plane = plane;
        fractal = f;
    }

    /**
     * Переопределение метода класса {@link Painter}
     * @param graphics  графика, с помощью которой будем рисовать на панели
     */
    @Override
    public void paint(Graphics graphics) {
        //проходим по всем пикселям на панели
        for (int i = 0; i < plane.getWidth(); i++){
            for (int j = 0; j < plane.getHeight(); j++){
                //находим их декартовы координаты
                var x = Converter.xScr2Crt(i, plane);
                var y = Converter.yScr2Crt(j, plane);
                //проверяем, принадлежит ли такая точка фракталу
                var is = fractal.isInSet(new Complex(x, y));
                //выбираем цвет в зависимости от полученного ответа
                Color c = (col!=null)?col.getColor(is):((is==1.0F)?Color.BLACK:Color.WHITE);
                graphics.setColor(c);
                //закрашиваем пиксель соответствующим цветом
                graphics.fillRect(i, j, 1, 1);
            }
        }
    }
}
