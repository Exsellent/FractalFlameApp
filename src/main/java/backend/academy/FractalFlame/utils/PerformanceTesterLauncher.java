package backend.academy.FractalFlame.utils;

import backend.academy.FractalFlame.components.AffineTransformation;
import backend.academy.FractalFlame.components.Color;
import backend.academy.FractalFlame.components.Rectangular;
import backend.academy.FractalFlame.config.Config;
import backend.academy.FractalFlame.renderers.RenderConfig;
import backend.academy.FractalFlame.renderers.RenderPerformanceTester;
import backend.academy.FractalFlame.transformations.ColorTransformation;
import backend.academy.FractalFlame.transformations.HyperbolicTransformation;
import backend.academy.FractalFlame.transformations.LinearTransformation;
import backend.academy.FractalFlame.transformations.SpiralTransformation;
import backend.academy.FractalFlame.transformations.Transformation;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс запуска тестов производительности рендеров.
 *
 * Используется для сравнения однопоточной и многопоточной реализации.
 */
public final class PerformanceTesterLauncher {

    private static final double DEFAULT_SCALE = 0.5;
    private static final int DEFAULT_COLOR_RED = 255;
    private static final int DEFAULT_COLOR_GREEN = 0;
    private static final int DEFAULT_COLOR_BLUE = 0;

    private PerformanceTesterLauncher() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Точка входа для запуска тестов производительности.
     *
     * @param args
     *            аргументы командной строки (не используются)
     */
    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        // Конфигурация рендера
        RenderConfig renderConfig = createRenderConfig();
        // Тестирование производительности
        RenderPerformanceTester tester = new RenderPerformanceTester(renderConfig);
        tester.runPerformanceTest();
    }

    /**
     * Создаёт конфигурацию рендера.
     *
     * @return конфигурация {@link RenderConfig}
     */

    private static RenderConfig createRenderConfig() {
        final int width = 1920;
        final int height = 1080;
        final Rectangular world = new Rectangular(-1, -1, 2, 2);
        final int symmetry = 3;
        final int threadCount = Runtime.getRuntime().availableProcessors();
        final int samples = 1_000_000;
        final int iterPerSample = 100;
        final int seed = 42;

        Config config = createDefaultConfig();
        List<ColorTransformation> affine = createAffineTransformations(config);
        List<Transformation> variations = createVariations();

        return new RenderConfig.Builder().setWidth(width).setHeight(height).setWorld(world).setAffine(affine)
                .setVariations(variations).setSymmetry(symmetry).setThreadCount(threadCount).setSamples(samples)
                .setIterPerSample(iterPerSample).setSeed(seed).build();
    }

    /**
     * Создаёт конфигурацию с предопределёнными аффинными преобразованиями.
     *
     * @return конфигурация {@link Config}
     */
    private static Config createDefaultConfig() {
        return new Config.Builder().setPresetAffineTransformations(new AffineTransformation[] {
                new AffineTransformation(DEFAULT_SCALE, 0, 0, 0, DEFAULT_SCALE, 0, DEFAULT_COLOR_RED,
                        DEFAULT_COLOR_GREEN, DEFAULT_COLOR_BLUE),
                new AffineTransformation(DEFAULT_SCALE, 0, DEFAULT_SCALE, 0, DEFAULT_SCALE, 0, DEFAULT_COLOR_GREEN,
                        DEFAULT_COLOR_RED, DEFAULT_COLOR_BLUE),
                new AffineTransformation(DEFAULT_SCALE, 0, 0, 0, DEFAULT_SCALE, DEFAULT_SCALE, DEFAULT_COLOR_GREEN,
                        DEFAULT_COLOR_GREEN, DEFAULT_COLOR_RED) })
                .build();
    }

    /**
     * Создаёт список аффинных преобразований на основе конфигурации.
     *
     * @param config
     *            конфигурация {@link Config}
     *
     * @return список преобразований {@link ColorTransformation}
     */
    private static List<ColorTransformation> createAffineTransformations(Config config) {
        List<ColorTransformation> affine = new ArrayList<>();
        for (AffineTransformation t : config.getPresetAffineTransformations()) {
            if (t != null) {
                affine.add(new ColorTransformation(new LinearTransformation(t.a(), t.b(), t.c(), t.d(), t.e(), t.f()),
                        new Color(t.red(), t.green(), t.blue())));
            }
        }
        return affine;
    }

    /**
     * Создаёт список нелинейных преобразований.
     *
     * @return список преобразований {@link Transformation}
     */
    private static List<Transformation> createVariations() {
        List<Transformation> variations = new ArrayList<>();
        variations.add(new HyperbolicTransformation());
        variations.add(new SpiralTransformation());
        return variations;
    }
}
