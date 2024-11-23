package backend.academy.FractalFlame.renderers;

import backend.academy.FractalFlame.components.Color;
import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Pixel;
import backend.academy.FractalFlame.components.Point;
import backend.academy.FractalFlame.components.Rectangular;
import backend.academy.FractalFlame.transformations.ColorTransformation;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Abstract class for rendering fractal images.
 * <p>
 * This class provides basic methods for rendering, such as rotating points, mixing colors, and updating
 * pixels.
 * </p>
 *
 * @since 1.0
 */
public abstract class AbstractRenderer implements Renderer {

    /**
     * Rotates the point by the specified angle.
     *
     * @param point
     *      point for rotation
     * @param theta
     *      angle of rotation in radians
     *
     * @return new point after rotation
     */
    protected Point rotate(Point point, double theta) {
        double cosTheta = Math.cos(theta);
        double sinTheta = Math.sin(theta);

        double newX = cosTheta * point.x() - sinTheta * point.y();
        double newY = sinTheta * point.x() + cosTheta * point.y();

        return new Point(newX, newY);
    }

    /**
     * Mixes two colors.
     *
     * @param first
     *      first color
     * @param second
     *      the second color
     *
     * @return a new color obtained by mixing the first two
     */
    protected Color mixColor(Color first, Color second) {
        return new Color((first.r() + second.r()) / 2, (first.g() + second.g()) / 2, (first.b() + second.b()) / 2);
    }

    /**
     * Calculates the extension coordinate.
     *
     * @param size
     *      Size
     * @param min
     *      minimum value
     * @param max
     *      maximum value
     * @param point
     *      point
     *
     * @return extension coordinate
     */
    protected int extension(int size, double min, double max, double point) {
        return size - (int) Math.ceil((max - point) / (max - min) * size);
    }

    /**
     * Updated Pixel on Holste.
     *
     * @param canvas
     *      Holst
     * @param colorTransformation
     *      color transformation
     * @param x
     *      coordinate
     * @param y
     *      coordinate
     */
    protected void updatePixel(IFractalImage canvas, ColorTransformation colorTransformation, int x, int y) {
        Pixel oldPixel = canvas.pixel(x, y);
        Color newColor = mixColor(oldPixel.color(), colorTransformation.color());
        int newHitCount = oldPixel.hitCount() + 1;
        canvas.updatePixel(x, y, new Pixel(newColor, newHitCount));
    }

    /**
     * Applies changes to the canvas.
     *
     * @param canvas
     *      canvas
     * @param world
     *      rectangular area
     * @param pw
     *      point
     * @param chosenAffine
     *      selected affine transformation
     */
    protected void applyChanges(IFractalImage canvas, Rectangular world, Point pw, ColorTransformation chosenAffine) {
        if (world.contains(pw)) {
            int canvasX = extension(canvas.getWidth(), world.x(), world.x() + world.width(), pw.x());
            int canvasY = extension(canvas.getHeight(), world.y(), world.y() + world.height(), pw.y());

            if (canvas.contains(canvasX, canvasY)) {
                updatePixel(canvas, chosenAffine, canvasX, canvasY);
            }
        }
    }

    /**
     * Generates a random point within a rectangular area.
     *
     * @param world
     *      rectangular area
     *
     * @return random point
     */
    protected Point randomPoint(Rectangular world) {
        double x = world.x() + (ThreadLocalRandom.current().nextDouble() * world.width());
        double y = world.y() + (ThreadLocalRandom.current().nextDouble() * world.height());
        return new Point(x, y);
    }

}
