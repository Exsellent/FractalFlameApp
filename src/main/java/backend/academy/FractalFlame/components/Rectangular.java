package backend.academy.FractalFlame.components;

/**
 * Represents a rectangle with the coordinates of the upper-left corner (x, y), width and height.
 *
 * @param x
 *      x coordinate of the upper-left corner
 * @param y
 *      the y coordinate of the upper-left corner
 * @param width
 *      rectangle width
 * @param height
 *      rectangle height
 */
public record Rectangular(double x, double y, double width, double height) {

    /**
     * Checks whether the point is contained inside the rectangle.
     *
     * @param p
     *      point for verification
     *
     * @return {@code true} if the point is inside the rectangle, otherwise {@code false}
     */
    public boolean contains(Point p) {
        return p.x() >= this.x && p.x() <= this.x + this.width && p.y() >= this.y && p.y() <= this.y + this.height;
    }
}
