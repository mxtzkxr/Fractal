package ru.smak.math;

import ru.smak.math.Complex;

/**
 * Интерфейс фрактала
 */
public interface Fractal {
    /**
     * Метод для проверки принадлежности точки фрактальному множеству
     * @param c точка, чью принадлежность мы проверяем
     * @return true если точка принадлежит, иначе false
     */
    float isInSet(Complex c);
}
