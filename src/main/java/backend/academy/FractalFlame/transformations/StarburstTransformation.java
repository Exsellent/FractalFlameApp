package backend.academy.FractalFlame.transformations;

import backend.academy.FractalFlame.components.Point;

@SuppressWarnings("checkstyle:MagicNumber")
public class StarburstTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = radius(point);
        double theta = theta(point);

        double r = Math.pow(Math.abs(Math.sin(3 * theta)) + Math.abs(Math.cos(2 * theta)), 0.5);
        double newX = radius * Math.cos(theta) / r;
        double newY = radius * Math.sin(theta) / r;

        return new Point(newX, newY);
    }
}
