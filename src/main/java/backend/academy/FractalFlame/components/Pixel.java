package backend.academy.FractalFlame.components;

/**
 * Представляет пиксель с цветом и количеством попаданий.
 *
 * @param color
 *            цвет пикселя
 * @param hitCount
 *            количество попаданий в пиксель
 */
public record Pixel(Color color, int hitCount) {
}
