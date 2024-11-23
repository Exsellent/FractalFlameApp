package backend.academy.FractalFlame.transformations;

import backend.academy.FractalFlame.components.Point;

public class CylinderTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double newX = Math.sin(point.x());
        double newY = point.y();
        double newZ = Math.cos(point.x());

        return new Point(newX, newY);
    }
}
