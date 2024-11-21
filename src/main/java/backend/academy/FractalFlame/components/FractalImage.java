package backend.academy.FractalFlame.components;

/**
 * Фрактальное изображение, использующее массив объектов {@link Pixel}. Содержит данные о пикселях, а также ширину и
 * высоту изображения.
 */
public record FractalImage(Pixel[] data, int width, int height) implements IFractalImage {

    /**
     * Создает новое фрактальное изображение с заданной шириной и высотой.
     *
     * @param width
     *            ширина изображения
     * @param height
     *            высота изображения
     *
     * @return новый объект {@code FractalImage}
     */
    public static FractalImage create(int width, int height) {
        Pixel[] data = new Pixel[width * height];
        for (int i = 0; i < data.length; i++) {
            data[i] = new Pixel(new Color(0, 0, 0), 0);
        }
        return new FractalImage(data, width, height);
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
     * Возвращает пиксель по заданным координатам (x, y).
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
            return data[y * width + x];
        }
        return null;
    }

    /**
     * Обновляет пиксель по заданным координатам (x, y) новым значением.
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
