package backend.academy.FractalFlame.transformations;

import backend.academy.FractalFlame.components.Point;

@SuppressWarnings("checkstyle:MagicNumber")
public class HorseshoeTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double radius = radius(point);

        double newX = (point.x() - point.y()) * (point.x() + point.y()) / radius;
        double newY = 2 * point.x() * point.y() / radius;

        return new Point(newX, newY);
    }
}
