package backend.academy.FractalFlame.transformations;

import backend.academy.FractalFlame.components.Point;

@SuppressWarnings("checkstyle:MagicNumber")
public class FisheyeTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double radius = radius(point);

        double newX = 2 * point.y() / (radius + 1);
        double newY = 2 * point.x() / (radius + 1);

        return new Point(newX, newY);
    }
}
