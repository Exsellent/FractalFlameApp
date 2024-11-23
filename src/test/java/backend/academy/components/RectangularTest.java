package backend.academy.components;

import backend.academy.FractalFlame.components.Point;

record Rectangular(double x, double y, double width, double height) {
    public boolean contains(Point p) {
        double rightBorder = this.x + this.width;
        double bottomBorder = this.y + this.height;

        return p.x() >= this.x && p.x() <= rightBorder && p.y() >= this.y && p.y() <= bottomBorder;
    }
}
