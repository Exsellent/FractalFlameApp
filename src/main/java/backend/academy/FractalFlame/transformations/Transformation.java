package backend.academy.FractalFlame.transformations;

import backend.academy.FractalFlame.components.Point;
import java.util.function.Function;

/**
 * Интерфейс для преобразований точек.
 * <p>
 * Этот интерфейс расширяет {@link Function} и предоставляет методы для вычисления различных характеристик точек, таких
 * как радиус и угол.
 * </p>
 *
 * @since 1.0
 */
public interface Transformation extends Function<Point, Point> {

    /**
     * Вычисляет радиус точки.
     *
     * @param point
     *            точка, для которой вычисляется радиус
     *
     * @return радиус точки
     */
    default double radius(Point point) {
        return Math.sqrt(point.x() * point.x() + point.y() * point.y());
    }

    /**
     * Вычисляет квадрат радиуса точки.
     *
     * @param point
     *            точка, для которой вычисляется квадрат радиуса
     *
     * @return квадрат радиуса точки
     */
    default double radiusSquared(Point point) {
        return point.x() * point.x() + point.y() * point.y();
    }

    /**
     * Вычисляет угол (тета) точки.
     *
     * @param point
     *            точка, для которой вычисляется угол
     *
     * @return угол точки в радианах
     */
    default double theta(Point point) {
        return Math.atan2(point.y(), point.x());
    }
}
