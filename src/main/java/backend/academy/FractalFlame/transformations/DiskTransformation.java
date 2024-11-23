package backend.academy.FractalFlame.transformations;

import backend.academy.FractalFlame.components.Point;

public class DiskTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double radius = radius(point);
        double theta = theta(point);

        double newX = Math.sin(theta) * Math.sin(Math.PI * radius);
        double newY = Math.cos(theta) * Math.sin(Math.PI * radius);
        double newZ = Math.cos(Math.PI * radius); // Добавляем объем за счет косинуса

        return new Point(newX, newY);
    }
}
