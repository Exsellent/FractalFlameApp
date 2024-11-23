package backend.academy.FractalFlame.renderers;

import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Rectangular;
import backend.academy.FractalFlame.transformations.ColorTransformation;
import backend.academy.FractalFlame.transformations.Transformation;
import java.util.List;

/**
 * Интерфейс для рендеринга фрактальных изображений с различными преобразованиями и параметрами.
 * <p>
 * Этот интерфейс определяет метод для рендеринга фрактальных изображений, используя заданные преобразования, вариации и
 * параметры.
 * </p>
 *
 * @since 1.0
 */
public interface Renderer {

    /**
     * Рендерит фрактальное изображение на холсте.
     *
     * @param canvas
     *            холст для рендеринга
     * @param world
     *            прямоугольная область мира
     * @param affine
     *            список цветовых преобразований
     * @param variations
     *            список вариаций преобразований
     * @param samples
     *            количество образцов
     * @param iterPerSample
     *            количество итераций на образец
     * @param seed
     *            начальное значение для генератора случайных чисел
     *
     * @return сгенерированное фрактальное изображение
     */
    IFractalImage render(IFractalImage canvas, Rectangular world, List<ColorTransformation> affine,
            List<Transformation> variations, int samples, int iterPerSample, int seed);
}
