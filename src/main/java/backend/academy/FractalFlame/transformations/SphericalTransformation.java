package backend.academy.FractalFlame.transformations;

import backend.academy.FractalFlame.components.Point;

public class SphericalTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double radiusSquared = point.x() * point.x() + point.y() * point.y();

        double newX = point.x() / radiusSquared;
        double newY = point.y() / radiusSquared;

        return new Point(newX, newY);
    }
}
