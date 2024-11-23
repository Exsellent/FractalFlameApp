package backend.academy.FractalFlame.renderers;

import backend.academy.FractalFlame.components.FractalImage;
import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Pixel;
import backend.academy.FractalFlame.components.Point;
import backend.academy.FractalFlame.components.Rectangular;
import backend.academy.FractalFlame.transformations.ColorTransformation;
import backend.academy.FractalFlame.transformations.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс для многопоточного рендеринга фрактальных изображений.
 * <p>
 * Этот класс реализует методы для рендеринга фрактальных изображений с использованием нескольких потоков для повышения
 * производительности.
 * </p>
 *
 * @since 1.0
 */
public class MultiRenderer extends AbstractRenderer {

    private final int symmetry;
    private final int threadCount;
    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * Конструктор для создания многопоточного рендерера.
     *
     * @param threadCount
     *            количество потоков
     * @param symmetry
     *            симметрия
     */
    public MultiRenderer(int threadCount, int symmetry) {
        this.threadCount = threadCount;
        this.symmetry = symmetry;
    }

    /**
     * Рендерит фрактальное изображение на холсте.
     *
     * @param canvas
     *            холст для рендеринга
     * @param world
     *            прямоугольная область мира
     * @param affine
     *            список цветовых преобразований
     * @param variations
     *            список вариаций преобразований
     * @param samples
     *            количество образцов
     * @param iterPerSample
     *            количество итераций на образец
     * @param seed
     *            начальное значение для генератора случайных чисел
     *
     * @return сгенерированное фрактальное изображение
     */
    @Override
    public IFractalImage render(IFractalImage canvas, Rectangular world, List<ColorTransformation> affine,
            List<Transformation> variations, int samples, int iterPerSample, int seed) {
        List<Callable<IFractalImage>> tasks = new ArrayList<>();
        int samplesPerThread = samples / threadCount;
        int remainingSamples = samples % threadCount;

        try (ExecutorService executorService = Executors.newFixedThreadPool(threadCount)) {
            for (int i = 0; i < threadCount; i++) {
                int finalI = i;
                tasks.add(() -> renderPart(FractalImage.create(canvas.getWidth(), canvas.getHeight()), world, affine,
                        variations, samplesPerThread + ((finalI < remainingSamples) ? 1 : 0), iterPerSample));

            }

            List<Future<IFractalImage>> results = executorService.invokeAll(tasks);
            for (Future<IFractalImage> result : results) {
                merge(canvas, result.get());
            }
        } catch (Exception exception) {
            LOGGER.error(exception);
        }

        return canvas;
    }

    private IFractalImage renderPart(IFractalImage canvas, Rectangular world, List<ColorTransformation> affine,
            List<Transformation> variations, int samples, int iterPerSample) {
        for (int i = 0; i < samples; i++) {
            Point pw = randomPoint(world);
            for (int step = 0; step < iterPerSample; step++) {
                ColorTransformation chosenAffine = affine.get(ThreadLocalRandom.current().nextInt(affine.size()));
                Transformation variation = variations.get(ThreadLocalRandom.current().nextInt(variations.size()));

                pw = variation.apply(chosenAffine.transformation().apply(pw));

                if (symmetry > 0) {
                    for (int s = 0; s < symmetry; s++) {
                        double theta = Math.PI * 2 / symmetry * s;
                        Point rotatedPoint = rotate(pw, theta);
                        applyChanges(canvas, world, rotatedPoint, chosenAffine);
                    }
                } else {
                    applyChanges(canvas, world, pw, chosenAffine);
                }
            }
        }
        return canvas;
    }

    private void merge(IFractalImage mainCanvas, IFractalImage partialCanvas) {
        int width = mainCanvas.getWidth();
        int height = mainCanvas.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel mainPixel = mainCanvas.pixel(x, y);
                Pixel partialPixel = partialCanvas.pixel(x, y);
                if (partialPixel != null) {
                    mainCanvas.updatePixel(x, y, mergePixels(mainPixel, partialPixel));
                }
            }
        }
    }

    private Pixel mergePixels(Pixel pixel1, Pixel pixel2) {
        return new Pixel(mixColor(pixel1.color(), pixel2.color()), pixel1.hitCount() + pixel2.hitCount());
    }
}
