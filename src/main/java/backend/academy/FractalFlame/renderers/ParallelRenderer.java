package backend.academy.FractalFlame.renderers;

import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Point;
import backend.academy.FractalFlame.components.Rectangular;
import backend.academy.FractalFlame.transformations.ColorTransformation;
import backend.academy.FractalFlame.transformations.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс для многопоточного рендеринга фрактальных изображений.
 * <p>
 * Этот класс реализует методы рендеринга фрактальных изображений с использованием нескольких потоков для повышения производительности
 *.
 * </p>
 *
 * @начиная с версии 1.0
 */
public class ParallelRenderer extends AbstractRenderer {

    private final int symmetry;
    private final int threadCount;

    /**
     * Constructor for creating a parallel renderer.
     *
     * @param threadCount
     *      number of streams
     * @param symmetry
     *      symmetry
     */
    public ParallelRenderer(int threadCount, int symmetry) {
        this.threadCount = threadCount;
        this.symmetry = symmetry;
    }

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
    @Override
    public IFractalImage render(IFractalImage canvas, Rectangular world, List<ColorTransformation> affine,
            List<Transformation> variations, int samples, int iterPerSample, int seed) {
        try (ExecutorService executor = Executors.newFixedThreadPool(threadCount)) {
            for (int i = 0; i < samples; i++) {
                executor.submit(() -> renderSample(canvas, world, affine, variations, iterPerSample));
            }
        }
        return canvas;
    }

    private void renderSample(IFractalImage canvas, Rectangular world, List<ColorTransformation> affine,
            List<Transformation> variations, int iterPerSample) {
        Point pw = randomPoint(world);

        for (int step = 0; step < iterPerSample; ++step) {
            ColorTransformation chosenAffine = affine.get(ThreadLocalRandom.current().nextInt(affine.size()));
            Transformation variation = variations.get(ThreadLocalRandom.current().nextInt(variations.size()));

            pw = variation.apply(chosenAffine.transformation().apply(pw));

            if (symmetry > 0) {
                for (int s = 0; s < symmetry; s++) {
                    double theta = Math.PI * 2 / symmetry * s;
                    Point pwr = rotate(pw, theta);

                    applyChanges(canvas, world, pwr, chosenAffine);
                }
            } else {
                applyChanges(canvas, world, pw, chosenAffine);
            }
        }
    }

}
