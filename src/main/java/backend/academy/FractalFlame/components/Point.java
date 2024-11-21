package backend.academy.FractalFlame.components;

/**
 * Представляет точку в трехмерном пространстве с координатами x, y и z.
 */
public class Point {
    private final double x;
    private final double y;
    private final double z;

    /**
     * Создает точку в двухмерном пространстве с координатами x и y. Координата z по умолчанию равна 0.0.
     *
     * @param x
     *            координата x
     * @param y
     *            координата y
     */
    public Point(double x, double y) {
        this(x, y, 0.0);
    }

    /**
     * Создает точку в трехмерном пространстве с координатами x, y и z.
     *
     * @param x
     *            координата x
     * @param y
     *            координата y
     * @param z
     *            координата z
     */
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Возвращает координату x.
     *
     * @return координата x
     */
    public double x() {
        return x;
    }

    /**
     * Возвращает координату y.
     *
     * @return координата y
     */
    public double y() {
        return y;
    }

    /**
     * Возвращает координату z.
     *
     * @return координата z
     */
    public double z() {
        return z;
    }
}
