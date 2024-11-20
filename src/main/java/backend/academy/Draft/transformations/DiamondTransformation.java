package backend.academy.FractalFlame.transformations;

import backend.academy.FractalFlame.components.Point;

public class DiamondTransformation implements Transformation {
    private static final double SCALE_X = 2.2;
    private static final double SCALE_Y = 2.2;

    @Override
    public Point apply(Point point) {
        double radius = radius(point);
        double theta = theta(point);

        double newX = Math.sin(theta) * Math.sin(radius);
        double newY = Math.cos(theta) * Math.cos(radius);

        // масштабирование для эффекта пирамиды Diamond
        newX *= SCALE_X;
        newY *= SCALE_Y;

        return new Point(newX, newY);
    }
}
