package backend.academy.FractalFlame.transformations;

import backend.academy.FractalFlame.components.Color;

/**
 * Entry to represent the color conversion.
 * <p>
 * This entry contains affine transformation and color, which are used for rendering fractal images.
 * </p>
 *
 * @param transformation
 * affine transformation
 * @param color
 * color
 *
 * @since 1.0
 */
public record ColorTransformation(Transformation transformation, Color color) {
}
