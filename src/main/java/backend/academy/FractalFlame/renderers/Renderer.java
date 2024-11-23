package backend.academy.FractalFlame.renderers;

import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Rectangular;
import backend.academy.FractalFlame.transformations.ColorTransformation;
import backend.academy.FractalFlame.transformations.Transformation;
import java.util.List;

/**
 * Interface for rendering fractal images with various transformations and parameters.
 * <p>
 * This interface defines a method for rendering fractal images using specified transformations, variations, and
 * parameters.
 * </p>
 *
 * @since 1.0
 */
public interface Renderer {

    /**
     * Renders a fractal image on canvas.
     *
     * @param canvas
     *      canvas for rendering
     * @param world
     *      rectangular area of the world
     * @param affine
     *      List of color transformations
     * @param variations
     *      List of transformation variations
     * @param samples
     *      number of samples
     * @param iterPerSample
     *      number of iterations per sample
     * @param seed
     *      the initial value for the random number generator
     *
     * @return generated fractal image
     */
    IFractalImage render(IFractalImage canvas, Rectangular world, List<ColorTransformation> affine,
            List<Transformation> variations, int samples, int iterPerSample, int seed);
}
