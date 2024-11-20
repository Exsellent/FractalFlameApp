package backend.academy.FractalFlame.transformations;

import backend.academy.FractalFlame.components.Point;

public class FlowerTransformation implements Transformation {
    private final int petals;
    private static final double SCALE_X = 1.5;
    private static final double SCALE_Y = 1.5;

    public FlowerTransformation(int petals) {
        this.petals = petals;
    }

    @Override
    public Point apply(Point point) {
        double radius = radius(point);
        double theta = theta(point);

        double r = Math.cos(petals * theta) * radius;
        double newX = r * Math.cos(theta);
        double newY = r * Math.sin(theta);

        newX *= SCALE_X;
        newY *= SCALE_Y;

        return new Point(newX, newY);
    }
}
