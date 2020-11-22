package ru.smak.math;

/**
 * Класс для работы с множеством Мандельброта, реализует интерфейс {@link Fractal}
 */
public class Mandelbrot implements Fractal{

    //ограничение итераций
    private int maxIters = 200;
    //Квадрат ограничивающего числа для удобства
    private double r2 = 4;

    /**
     * Метод для задания ограничения числа итераций, но не менее пяти
     * @param value  желаемое ограничение
     */
    public void setMaxIters(int value){
        maxIters = Math.max(5, value);
    }

    /**
     *Метод для задания R в основном неравенстве, задающем множество
     * @param value  радиус
     */
    public void setR(int value){
        var r = Math.max(0.1, value);
        r2 = r*r;
    }

    /**
     * Реализация метода принадлежности точки из {@link Fractal}
     * Точка c принадлежит множеству, если для нее выполнено реккурентное неравенство
     * z_(n+1) = (z_(n))^2 + c; |z_(k)|<R для всех натуральных k.
     * В нашем случае k ограничена числом maxIters
     * @param c точка, чью принадлежность мы проверяем
     * @return true если принадлежит, в противном случае - false
     */
    @Override
    public float isInSet(Complex c) {
        var z = new Complex();
        for (int i = 0; i<maxIters; i++){
            z = z.times(z).plus(c);
            if (z.abs2() > r2) return (float)i/maxIters;
        }
        return 1.0F;

    }
}
