package backend.academy.FractalFlame.transformations;

import backend.academy.FractalFlame.components.Color;

/**
 * Запись для представления цветового преобразования.
 * <p>
 * Эта запись содержит аффинное преобразование и цвет, которые используются для рендеринга фрактальных изображений.
 * </p>
 *
 * @param transformation
 *            аффинное преобразование
 * @param color
 *            цвет
 *
 * @since 1.0
 */
public record ColorTransformation(Transformation transformation, Color color) {
}
