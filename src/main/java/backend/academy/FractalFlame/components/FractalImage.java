package backend.academy.FractalFlame.components;

/**
 * A fractal image using an array of {@link Pixel} objects. Contains pixel data, as well as the width and
 * height of the image.
 */
public record FractalImage(Pixel[] data, int width, int height) implements IFractalImage {

    /**
     * Creates a new fractal image with the specified width and height.
     *
     * @param width
     *      Image width
     * @param height
     *      Image height
     *
     * @return new object {@code FractalImage}
     */
    public static FractalImage create(int width, int height) {
        Pixel[] data = new Pixel[width * height];
        for (int i = 0; i < data.length; i++) {
            data[i] = new Pixel(new Color(0, 0, 0), 0);
        }
        return new FractalImage(data, width, height);
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
    public boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

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
    public Pixel pixel(int x, int y) {
        if (contains(x, y)) {
            return data[y * width + x];
        }
        return null;
    }

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
    public void updatePixel(int x, int y, Pixel newPixel) {
        if (contains(x, y)) {
            data[y * width + x] = newPixel;
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Pixel[] getData() {
        return data;
    }
}
