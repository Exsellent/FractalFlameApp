package backend.academy.FractalFlame.components;

/**
 * Представляет синхронизированное фрактальное изображение с пикселями и механизмом блокировки для потокобезопасного
 * доступа.
 *
 * @param data
 *            массив пикселей изображения
 * @param width
 *            ширина изображения
 * @param height
 *            высота изображения
 * @param locks
 *            массив объектов для синхронизации доступа к пикселям
 */
public record SyncFractalImage(Pixel[] data, int width, int height, Object[] locks)
    implements IFractalImage {

    /**
     * Создает новое синхронизированное фрактальное изображение с заданной шириной и высотой.
     *
     * @param width
     *            ширина изображения
     * @param height
     *            высота изображения
     *
     * @return новый объект {@code SyncFractalImage}
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
     * Проверяет, содержится ли точка с координатами (x, y) внутри изображения.
     *
     * @param x
     *            координата x
     * @param y
     *            координата y
     *
     * @return {@code true}, если точка находится внутри изображения, иначе {@code false}
     */
    public boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /**
     * Возвращает пиксель по заданным координатам (x, y) с использованием синхронизации.
     *
     * @param x
     *            координата x
     * @param y
     *            координата y
     *
     * @return пиксель по заданным координатам или {@code null}, если координаты вне изображения
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
     * Обновляет пиксель по заданным координатам (x, y) новым значением с использованием синхронизации.
     *
     * @param x
     *            координата x
     * @param y
     *            координата y
     * @param newPixel
     *            новый пиксель
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
