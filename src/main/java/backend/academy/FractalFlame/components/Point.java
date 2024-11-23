package backend.academy.FractalFlame.components;

/**
 * Represents a point in a two-dimensional Cartesian coordinate system. This class is immutable and uses the
 * {@code record} feature of Java.
 */
public record Point(double x, double y) {

    /**
     * Compares this point to the specified object for equality.
     *
     * @param o
     *            the object to compare this point with
     *
     * @return {@code true} if the specified object is equal to this point; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    /**
     * Returns the hash code for this point.
     *
     * @return the hash code for this point
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y);
    }
}
