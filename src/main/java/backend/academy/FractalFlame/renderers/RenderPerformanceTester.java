package backend.academy.FractalFlame.renderers;

import backend.academy.FractalFlame.components.FractalImage;
import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Rectangular;
import backend.academy.FractalFlame.transformations.ColorTransformation;
import backend.academy.FractalFlame.transformations.Transformation;
import backend.academy.FractalFlame.utils.PerformanceMeasurer;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс для тестирования производительности рендеров.
 */
public class RenderPerformanceTester {

    private static final Logger LOGGER = LogManager.getLogger(RenderPerformanceTester.class);
    private static final double NANOSECONDS_TO_SECONDS = 1_000_000_000.0;
    private static final String TIME_FORMAT = "%.3f";
    private static final String SPEEDUP_FORMAT = "%.2f";

    private final SingleRenderer singleRenderer;
    private final ParallelRenderer parallelRenderer;
    private final IFractalImage canvas;
    private final Rectangular world;
    private final List<ColorTransformation> affine;
    private final List<Transformation> variations;
    private final int samples;
    private final int iterPerSample;
    private final int seed;

    public RenderPerformanceTester(RenderConfig config) {
        this.canvas = FractalImage.create(config.getWidth(), config.getHeight());
        this.world = config.getWorld();
        this.affine = config.getAffine();
        this.variations = config.getVariations();
        this.samples = config.getSamples();
        this.iterPerSample = config.getIterPerSample();
        this.seed = config.getSeed();

        this.singleRenderer = new SingleRenderer(config.getSymmetry());
        this.parallelRenderer = new ParallelRenderer(config.getThreadCount(), config.getSymmetry());
    }

    public void runPerformanceTest() {

        long singleThreadDuration = PerformanceMeasurer.measureExecutionTime(
                () -> singleRenderer.render(FractalImage.create(canvas.getWidth(), canvas.getHeight()), world, affine,
                        variations, samples, iterPerSample, seed));
        double singleThreadInSeconds = singleThreadDuration / NANOSECONDS_TO_SECONDS;
        LOGGER.info("Однопоточная реализация заняла: {} секунд", String.format(TIME_FORMAT, singleThreadInSeconds));

        long multiThreadDuration = PerformanceMeasurer.measureExecutionTime(
                () -> parallelRenderer.render(FractalImage.create(canvas.getWidth(), canvas.getHeight()), world, affine,
                        variations, samples, iterPerSample, seed));
        double multiThreadInSeconds = multiThreadDuration / NANOSECONDS_TO_SECONDS;
        LOGGER.info("Многопоточная реализация заняла: {} секунд", String.format(TIME_FORMAT, multiThreadInSeconds));

        double speedup = singleThreadInSeconds / multiThreadInSeconds;
        LOGGER.info("Ускорение: {}x", String.format(SPEEDUP_FORMAT, speedup));
    }
}
