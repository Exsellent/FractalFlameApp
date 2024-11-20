package backend.academy.FractalFlame.transformations;

import backend.academy.FractalFlame.components.Point;

@SuppressWarnings("checkstyle:MagicNumber")
public class MandalaTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = radius(point);
        double theta = theta(point);

        @SuppressWarnings("checkstyle:MagicNumber")
        double r = Math.sin(6 * theta) * Math.cos(4 * radius);
        double newX = radius * Math.cos(theta) + r;
        double newY = radius * Math.sin(theta) + r;

        return new Point(newX, newY);
    }
}
