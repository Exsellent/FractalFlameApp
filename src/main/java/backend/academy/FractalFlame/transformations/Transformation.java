package backend.academy.FractalFlame.transformations;

import backend.academy.FractalFlame.components.Point;
import java.util.function.Function;

/**
 * Interface for point transformations.
 * <p>
 * This interface extends the {@link Function} and provides methods for calculating various characteristics of points, such
 as radius and angle.
 * </p>
 *
 * @since 1.0
 */
public interface Transformation extends Function<Point, Point> {

    /**
     * Calculates the radius of a point.
     *
     * @param point
     *      the point for which the radius is calculated
     *
     * @return point radius
     */
    default double radius(Point point) {
        return Math.sqrt(point.x() * point.x() + point.y() * point.y());
    }

    /**
     * Calculates the square of the radius of the point.
     *
     * @param point
     *      the point for which the square of the radius is calculated
     *
     * @return the square of the point radius
     */
    default double radiusSquared(Point point) {
        return point.x() * point.x() + point.y() * point.y();
    }

    /**
     * Calculates the angle (theta) of a point.
     *
     * @param point
     * the point for which the angle is calculated
     *
     * @return the angle of the point in radians
     */
    default double theta(Point point) {
        return Math.atan2(point.y(), point.x());
    }
}
