package backend.academy.FractalFlame.components;

/**
 * Represents a pixel with color and number of hits.
 *
 * @param color
 *      Pixel color
 * @param hitCount
 *      number of hits per pixel
 */
public record Pixel(Color color, int hitCount) {
}
