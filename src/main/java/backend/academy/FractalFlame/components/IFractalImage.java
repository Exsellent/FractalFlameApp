package backend.academy.FractalFlame.components;

/**
 * Интерфейс, представляющий фрактальное изображение.
 */
public interface IFractalImage {

    /**
     * Создает новое фрактальное изображение с заданной шириной и высотой.
     *
     * @param width  ширина изображения
     * @param height высота изображения
     * @return новый объект {@code IFractalImage}
     */
    static IFractalImage create(int width, int height) {
        return null;
    }

    /**
     * Проверяет, содержится ли точка с координатами (x, y) внутри изображения.
     *
     * @param x координата x
     * @param y координата y
     * @return {@code true}, если точка находится внутри изображения, иначе {@code false}
     */
    boolean contains(int x, int y);

    /**
     * Возвращает пиксель по заданным координатам (x, y).
     *
     * @param x координата x
     * @param y координата y
     * @return пиксель по заданным координатам или {@code null}, если координаты вне изображения
     */
    Pixel pixel(int x, int y);

    /**
     * Обновляет пиксель по заданным координатам (x, y) новым значением.
     *
     * @param x координата x
     * @param y координата y
     * @param newPixel новый пиксель
     */
    void updatePixel(int x, int y, Pixel newPixel);

    /**
     * Возвращает ширину изображения.
     *
     * @return ширина изображения
     */
    int getWidth();

    /**
     * Возвращает высоту изображения.
     *
     * @return высота изображения
     */
    int getHeight();

    /**
     * Возвращает массив пикселей изображения.
     *
     * @return массив пикселей
     */
    Pixel[] getData();
}
