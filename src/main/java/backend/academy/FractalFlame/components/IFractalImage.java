package backend.academy.FractalFlame.components;

public interface IFractalImage {

    /**
     * Creates a new fractal image with the specified width and height.
     *
     * @param width
     *      Image width
     * @param height
     *      Image height
     *
     * @return new object {@code IFractalImage}
     */
    static IFractalImage create(int width, int height) {
        return null;
    }

    /**
     * Checks whether a point with coordinates (x, y) is contained inside the image.
     *
     * @param x
     *      x coordinate
     * @param y
     *      y coordinate
     *
     * @return {@code true} if the point is inside the image, otherwise {@code false}
     */
    boolean contains(int x, int y);

    /**
     * Returns a pixel at the specified coordinates (x, y).
     *
     * @param x
     *      x coordinate
     * @param y
     *      y coordinate
     *
     * @return pixel at the specified coordinates or {@code null} if the coordinates are outside the image
     */
    Pixel pixel(int x, int y);

    /**
     * Updates the pixel at the specified coordinates (x, y) with a new value.
     *
     * @param x
     *      x coordinate
     * @param y
     *      y coordinate
     * @param newPixel
     *      new pixel
     */
    void updatePixel(int x, int y, Pixel newPixel);

    /**
     * Returns the width of the image.
     *
     * @return image width
     */
    int getWidth();

    /**
     * Returns the height of the image.
     *
     * @return image height
     */
    int getHeight();

    /**
     * Returns an array of image pixels.
     *
     * @return pixel array
     */
    Pixel[] getData();
}
