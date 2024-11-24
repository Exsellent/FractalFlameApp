package backend.academy.FractalFlame;

import backend.academy.FractalFlame.components.AffineTransformation;
import backend.academy.FractalFlame.components.Color;
import backend.academy.FractalFlame.components.FractalImage;
import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Rectangular;
import backend.academy.FractalFlame.components.SyncFractalImage;
import backend.academy.FractalFlame.config.Config;
import backend.academy.FractalFlame.processors.GammaCorrectionProcessor;
import backend.academy.FractalFlame.processors.ImageProcessor;
import backend.academy.FractalFlame.renderers.ParallelRenderer;
import backend.academy.FractalFlame.renderers.Renderer;
import backend.academy.FractalFlame.renderers.SingleRenderer;
import backend.academy.FractalFlame.transformations.ColorTransformation;
import backend.academy.FractalFlame.transformations.LinearTransformation;
import backend.academy.FractalFlame.utils.ImageUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Основной класс приложения для генерации и сохранения фрактальных изображений.
 * <p>
 * Этот класс содержит методы для настройки и запуска процесса генерации фракталов, а также для сохранения
 * сгенерированных изображений.
 * </p>
 *
 * @since 1.0
 */
public class FractalFlameApp {
    private static final Logger LOGGER = LogManager.getLogger(FractalFlameApp.class);

    private FractalFlameApp() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Генерирует и сохраняет фрактальное изображение на основе конфигурации.
     *
     * @param config
     *            конфигурация для генерации фрактала
     */
    public static void generateAndSaveFractal(Config config) {
        try {
            // Подготовка трансформаций
            List<ColorTransformation> affineTransformations = prepareAffineTransformations(
                    config.getRandomAffineTransformationsCount(), config.getPresetAffineTransformations());

            // Выбор рендерера
            Renderer renderer = createRenderer(config);

            // Создание холста
            IFractalImage canvas = createCanvas(config);

            // Рендеринг
            IFractalImage fractalImage = renderer.render(canvas, createWorldRect(config), affineTransformations,
                    Arrays.asList(config.getNonlinearTransformations()), config.getSamples(), config.getIterations(),
                    config.getSeed());

            // Коррекция изображения, если указано
            if (config.isWithCorrection()) {
                applyGammaCorrection(fractalImage, config.getGamma());
            }

            // Сохранение изображения
            saveFractalImage(fractalImage, config);
        } catch (Exception e) {
            LOGGER.error("Error during fractal generation and saving", e);
        }
    }

    /**
     * Создаёт рендерер на основе конфигурации.
     */
    private static Renderer createRenderer(Config config) {
        return config.getThreadsCount() <= 1 ? new SingleRenderer(config.getSymmetry())
                : new ParallelRenderer(config.getThreadsCount(), config.getSymmetry());
    }

    /**
     * Создаёт холст для рендеринга.
     */
    private static IFractalImage createCanvas(Config config) {
        return config.getThreadsCount() <= 1 ? FractalImage.create(config.getWidth(), config.getHeight())
                : SyncFractalImage.create(config.getWidth(), config.getHeight());
    }

    /**
     * Создаёт прямоугольник мира для рендеринга.
     */
    private static Rectangular createWorldRect(Config config) {
        return new Rectangular(config.getMinX(), config.getMinY(), config.getMaxX() - config.getMinX(),
                config.getMaxY() - config.getMinY());
    }

    /**
     * Применяет гамма-коррекцию к изображению.
     */
    private static void applyGammaCorrection(IFractalImage image, double gamma) {
        ImageProcessor processor = new GammaCorrectionProcessor(gamma);
        processor.process(image);
    }

    /**
     * Сохраняет сгенерированное фрактальное изображение.
     */
    private static void saveFractalImage(IFractalImage image, Config config) throws IOException {
        Path outputDir = Path.of(config.getDirectory());
        if (!Files.exists(outputDir)) {
            Files.createDirectories(outputDir);
        }

        Path outputPath = outputDir.resolve(config.getFilename() + "." + config.getFileType().toString().toLowerCase());

        ImageUtils.save(image, outputPath, config.getFileType());
        LOGGER.info("Fractal image saved to: {}", outputPath);
    }

    /**
     * Подготавливает список аффинных преобразований.
     */
    private static List<ColorTransformation> prepareAffineTransformations(int randomCount,
            AffineTransformation[] preset) {
        List<ColorTransformation> transformations = new ArrayList<>();

        // Генерация случайных аффинных преобразований
        for (int i = 0; i < randomCount; i++) {
            transformations.add(new ColorTransformation(LinearTransformation.randomTransformation(), Color.generate()));
        }

        // Добавление предустановленных преобразований
        if (preset != null) {
            for (AffineTransformation affine : preset) {
                transformations.add(new ColorTransformation(new LinearTransformation(affine.a(), affine.b(), affine.c(),
                        affine.d(), affine.e(), affine.f()), new Color(affine.red(), affine.green(), affine.blue())));
            }
        }

        return transformations;
    }
}
