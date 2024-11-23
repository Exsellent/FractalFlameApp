package backend.academy.FractalFlame.components;

/**
 * Affine transformation with coefficients and color information.
 * <p>
 * The affine transformation is determined by six coefficients (a, b, c, d, e, f), which are used for
 * coordinate transformation, as well as color information (red, green, blue).

 *
 * @param a
 *      coefficient for x
 * @param b
 *      coefficient for y
 * @param c
 *      coefficient for x
 * @param d
 *      the coefficient for y
 * @param e
 *      x offset
 * @param f
 *      y offset
 * @param red
 *      red component (0-255)
 * @param green
 *      green color component (0-255)
 * @param blue
 *      Blue component (0-255)
 */

@SuppressWarnings("checkstyle:RecordComponentNumber")
public record AffineTransformation(double a, double b, double c, double d, double e, double f, int red, int green,
        int blue) {
}
