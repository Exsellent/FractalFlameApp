package backend.academy.FractalFlame.components;

/**
 * Represents a synchronized fractal image with pixels and a locking mechanism for thread-safe
 * Access.
 *
 * @param data
 *      Image pixel array
 * @param width
 *      Image width
 * @param height
 *      image height
 * @param locks
 *      an array of objects for synchronizing access to pixels
 */
public record SyncFractalImage(Pixel[] data, int width, int height, Object[] locks)
    implements IFractalImage {

    /**
     * Creates a new synchronized fractal image with the specified width and height.
     *
     * @param width
     *      Image width
     * @param height
     *      Image height
     *
     * @return new object {@code SyncFractalImage}
     */
    public static SyncFractalImage create(int width, int height) {
        Pixel[] data = new Pixel[width * height];
        for (int i = 0; i < data.length; i++) {
            data[i] = new Pixel(new Color(0, 0, 0), 0);
        }

        Object[] locks = new Object[width * height];
        for (int i = 0; i < locks.length; i++) {
            locks[i] = new Object();
        }
        return new SyncFractalImage(data, width, height, locks);
    }

    /**
     * Checks whether a point with coordinates (x, y) is contained inside the image.
     *
     * @param x
     * x coordinate
     * @param y
     * y coordinate
     *
     * @return {@code true} if the point is inside the image, otherwise {@code false}
     */
    public boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /**
     * Returns a pixel at the specified coordinates (x, y) using synchronization.
     *
     * @param x
     * x coordinate
     * @param y
     * y coordinate
     *
     * @return pixel at the specified coordinates or {@code null} if the coordinates are outside the image
     */
    public Pixel pixel(int x, int y) {
        if (contains(x, y)) {
            synchronized (locks[y * width + x]) {
                return data[y * width + x];
            }
        }
        return null;
    }

    /**
     * Updates a pixel at the specified coordinates (x, y) with a new value using synchronization.
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
            synchronized (locks[y * width + x]) {
                data[y * width + x] = newPixel;
            }
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
