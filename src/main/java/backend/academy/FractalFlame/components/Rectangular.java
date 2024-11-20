package backend.academy.FractalFlame.components;

/**
 * Представляет прямоугольник с координатами верхнего левого угла (x, y),
 * шириной и высотой.
 *
 * @param x      координата x верхнего левого угла
 * @param y      координата y верхнего левого угла
 * @param width  ширина прямоугольника
 * @param height высота прямоугольника
 */
public record Rectangular(double x, double y, double width, double height) {

    /**
     * Проверяет, содержится ли точка внутри прямоугольника.
     *
     * @param p точка для проверки
     * @return {@code true}, если точка находится внутри прямоугольника, иначе {@code false}
     */
    public boolean contains(Point p) {
        return p.x() >= this.x && p.x() <= this.x + this.width && p.y()
            >= this.y && p.y() <= this.y + this.height;
    }
}
