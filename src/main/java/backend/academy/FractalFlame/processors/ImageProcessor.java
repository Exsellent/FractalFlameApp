package backend.academy.FractalFlame.processors;

import backend.academy.FractalFlame.components.IFractalImage;

/**
 * Функциональный интерфейс для обработки фрактальных изображений.
 * <p>
 * Этот интерфейс определяет метод для обработки объектов {@link IFractalImage}.
 * </p>
 *
 * @since 1.0
 */
@FunctionalInterface
public interface ImageProcessor {

    /**
     * Обрабатывает фрактальное изображение.
     *
     * @param image
     *            фрактальное изображение для обработки
     */
    void process(IFractalImage image);
}
