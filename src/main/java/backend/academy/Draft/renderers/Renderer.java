package backend.academy.FractalFlame.renderers;

import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Rectangular;
import backend.academy.FractalFlame.transformations.ColorTransformation;
import backend.academy.FractalFlame.transformations.Transformation;
import java.util.List;

/**
 * Interface for rendering fractal images with various transformations and parameters.
 */
public interface Renderer {

    IFractalImage render(IFractalImage canvas, Rectangular world, List<ColorTransformation> affine,
            List<Transformation> variations, int samples, int iterPerSample, int seed);
}
