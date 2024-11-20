package backend.academy.FractalFlame.transformations;

import backend.academy.FractalFlame.components.Point;

public class SpiralTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double radius = radius(point);
        double theta = theta(point);

        double newX = (Math.cos(theta) + Math.sin(radius)) / radius;
        double newY = (Math.sin(theta) - Math.cos(radius)) / radius;

        return new Point(newX, newY);
    }
}
