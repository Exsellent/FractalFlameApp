package backend.academy.FractalFlame.components;

/**
 * Представляет точку в двумерной декартовой системе координат. Этот класс неизменяем и использует функцию
 * {@code record} в Java.
 */
public record Point(double x, double y) {

    /**
     * Сравнивает эту точку с указанным объектом на равенство.
     *
     * @param o
     *            объект для сравнения с этой точкой
     *
     * @return {@code true}, если указанный объект равен этой точке; {@code false} в противном случае
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    /**
     * Возвращает хэш-код для этой точки.
     *
     * @return хэш-код для этой точки
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y);
    }
}
