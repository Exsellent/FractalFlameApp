package backend.academy.FractalFlame.transformations;

import backend.academy.FractalFlame.components.Point;

public class SwirlTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radiusSquared = radiusSquared(point);

        double sinR = Math.sin(radiusSquared);
        double cosR = Math.cos(radiusSquared);

        double newX = point.x() * sinR - point.y() * cosR;
        double newY = point.x() * cosR + point.y() * sinR;

        return new Point(newX, newY);
    }
}
