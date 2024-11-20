package backend.academy.FractalFlame.components;

/**
 * Аффинное преобразование с коэффициентами и информацией о цвете.
 * <p>
 * Аффинное преобразование определяется шестью коэффициентами (a, b, c, d, e, f),
 * которые используются для преобразования координат, а также цветовой информацией
 * (red, green, blue).
 * </p>
 *
 * @param a коэффициент для x
 * @param b коэффициент для y
 * @param c коэффициент для x
 * @param d коэффициент для y
 * @param e смещение по x
 * @param f смещение по y
 * @param red компонент красного цвета (0-255)
 * @param green компонент зеленого цвета (0-255)
 * @param blue компонент синего цвета (0-255)
 */
@SuppressWarnings("checkstyle:RecordComponentNumber")
public record AffineTransformation(double a, double b, double c, double d,
                                   double e, double f, int red, int green,
                                   int blue) {
}
