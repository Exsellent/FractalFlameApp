package backend.academy.FractalFlame.transformations;

import backend.academy.FractalFlame.components.Point;
import java.util.Random;

/**
 * Класс для линейных преобразований точек.
 * <p>
 * Этот класс реализует интерфейс {@link Transformation} и предоставляет методы для применения линейных преобразований к
 * точкам, а также генерации случайных линейных преобразований.
 * </p>
 *
 * @since 1.0
 */
public class LinearTransformation implements Transformation {
    private static final int BORDER = 2;
    private static final Random RANDOM = new Random();

    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double e;
    private final double f;

    /**
     * Конструктор для создания линейного преобразования с заданными коэффициентами.
     *
     * @param a
     *            коэффициент для x
     * @param b
     *            коэффициент для y
     * @param c
     *            смещение для x
     * @param d
     *            коэффициент для x
     * @param e
     *            коэффициент для y
     * @param f
     *            смещение для y
     */
    public LinearTransformation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    /**
     * Применяет линейное преобразование к заданной точке.
     *
     * @param point
     *            точка для преобразования
     *
     * @return новая точка после преобразования
     */
    @Override
    public Point apply(Point point) {
        double x = a * point.x() + b * point.y() + c;
        double y = d * point.x() + e * point.y() + f;
        return new Point(x, y);
    }

    /**
     * Генерирует случайное линейное преобразование.
     *
     * @return случайное линейное преобразование
     */
    @SuppressWarnings("MultipleVariableDeclarations")
    public static LinearTransformation randomTransformation() {
        double a, b, c, d, e, f;

        do {
            a = nextRandomCoefficient();
            d = nextRandomCoefficientWithCondition(a);

            b = nextRandomCoefficient();
            e = nextRandomCoefficientWithCondition(b);
        } while (!isValidCombination(a, b, d, e));

        c = RANDOM.nextDouble(-BORDER, BORDER);
        f = RANDOM.nextDouble(-BORDER, BORDER);

        return new LinearTransformation(a, b, c, d, e, f);
    }

    private static double nextRandomCoefficient() {
        return RANDOM.nextDouble() * 2 - 1;
    }

    private static double nextRandomCoefficientWithCondition(double coeff) {
        double result;
        do {
            result = Math.sqrt(RANDOM.nextDouble()) * (RANDOM.nextBoolean() ? 1 : -1);
        } while (coeff * coeff + result * result > 1);
        return result;
    }

    private static boolean isValidCombination(double a, double b, double d, double e) {
        return (a * a + b * b + d * d + e * e) <= (1 + (a * e - d * b) * (a * e - d * b));
    }
}
