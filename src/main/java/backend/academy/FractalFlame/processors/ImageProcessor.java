package backend.academy.FractalFlame.processors;

import backend.academy.FractalFlame.components.IFractalImage;

/**
 * Functional interface for fractal image processing.
 * <p>
 * This interface defines a method for processing {@link IFractalImage} objects.
 * </p>
 *
 * @since 1.0
 */
@FunctionalInterface
public interface ImageProcessor {

    /**
     * Processes a fractal image.
     *
     * @param image
     *      fractal image for processing
     */
    void process(IFractalImage image);
}
