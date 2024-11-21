package backend.academy.FractalFlame.renderers;

import backend.academy.FractalFlame.components.Color;
import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Pixel;
import backend.academy.FractalFlame.components.Point;
import backend.academy.FractalFlame.components.Rectangular;
import backend.academy.FractalFlame.transformations.ColorTransformation;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Абстрактный класс для рендеринга фрактальных изображений.
 * <p>
 * Этот класс предоставляет базовые методы для рендеринга, такие как вращение точек,
 * смешивание цветов и обновление пикселей.
 * </p>
 *
 * @since 1.0
 */
public abstract class AbstractRenderer implements Renderer {

    /**
     * Вращает точку на заданный угол.
     *
     * @param point точка для вращения
     * @param theta угол вращения в радианах
     * @return новая точка после вращения
     */
    protected Point rotate(Point point, double theta) {
        double cosTheta = Math.cos(theta);
        double sinTheta = Math.sin(theta);

        double newX = cosTheta * point.x() - sinTheta * point.y();
        double newY = sinTheta * point.x() + cosTheta * point.y();

        return new Point(newX, newY);
    }

    /**
     * Смешивает два цвета.
     *
     * @param first  первый цвет
     * @param second второй цвет
     * @return новый цвет, полученный путем смешивания первых двух
     */
    protected Color mixColor(Color first, Color second) {
        return new Color((first.r() + second.r()) / 2, (first.g() + second.g()) / 2, (first.b() + second.b()) / 2);
    }

    /**
     * Вычисляет координату расширения.
     *
     * @param size  размер
     * @param min   минимальное значение
     * @param max   максимальное значение
     * @param point точка
     * @return координата расширения
     */
    protected int extension(int size, double min, double max, double point) {
        return size - (int) Math.ceil((max - point) / (max - min) * size);
    }

    /**
     * Обновляет пиксель на холсте.
     *
     * @param canvas              холст
     * @param colorTransformation цветовое преобразование
     * @param x                   координата x
     * @param y                   координата y
     */
    protected void updatePixel(IFractalImage canvas, ColorTransformation colorTransformation, int x, int y) {
        Pixel oldPixel = canvas.pixel(x, y);
        Color newColor = mixColor(oldPixel.color(), colorTransformation.color());
        int newHitCount = oldPixel.hitCount() + 1;
        canvas.updatePixel(x, y, new Pixel(newColor, newHitCount));
    }

    /**
     * Применяет изменения к холсту.
     *
     * @param canvas       холст
     * @param world        прямоугольная область
     * @param pw           точка
     * @param chosenAffine выбранное аффинное преобразование
     */
    protected void applyChanges(IFractalImage canvas, Rectangular world, Point pw, ColorTransformation chosenAffine) {
        if (world.contains(pw)) {
            int canvasX = extension(canvas.getWidth(), world.x(), world.x() + world.width(), pw.x());
            int canvasY = extension(canvas.getHeight(), world.y(), world.y() + world.height(), pw.y());

            if (canvas.contains(canvasX, canvasY)) {
                updatePixel(canvas, chosenAffine, canvasX, canvasY);
            }
        }
    }

    /**
     * Генерирует случайную точку в пределах прямоугольной области.
     *
     * @param world прямоугольная область
     * @return случайная точка
     */
    protected Point randomPoint(Rectangular world) {
        double x = world.x() + (ThreadLocalRandom.current().nextDouble() * world.width());
        double y = world.y() + (ThreadLocalRandom.current().nextDouble() * world.height());
        return new Point(x, y);
    }

}
