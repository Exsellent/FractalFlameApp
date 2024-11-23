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
    private static final Logger LOGGER = LogManager.getLogger();

    private FractalFlameApp() {
    }

    /**
     * Генерирует и сохраняет фрактальное изображение на основе конфигурации.
     *
     * @param config
     *            конфигурация для генерации фрактала
     */
    public static void generateAndSaveFractal(Config config) {
        List<ColorTransformation> affineTransformations = prepareAffineTransformations(
                config.getRandomAffineTransformationsCount(), config.getPresetAffineTransformations());

        Renderer renderer = config.getThreadsCount() <= 1 ? new SingleRenderer(config.getSymmetry())
                : new ParallelRenderer(config.getThreadsCount(), config.getSymmetry());

        IFractalImage imageBase = config.getThreadsCount() <= 1
                ? FractalImage.create(config.getWidth(), config.getHeight())
                : SyncFractalImage.create(config.getWidth(), config.getHeight());

        IFractalImage fractalImage = renderer.render(imageBase,
                new Rectangular(config.getMinX(), config.getMinY(), config.getMaxX() - config.getMinX(),
                        config.getMaxY() - config.getMinY()),
                affineTransformations, Arrays.stream(config.getNonlinearTransformations()).toList(),
                config.getSamples(), config.getIterations(), config.getSeed());

        if (config.isWithCorrection()) {
            ImageProcessor processor = new GammaCorrectionProcessor(config.getGamma());
            processor.process(fractalImage);
        }

        try {
            ImageUtils.save(fractalImage,
                    Path.of(config.getDirectory(),
                            config.getFilename() + "." + config.getFileType().toString().toLowerCase()),
                    config.getFileType());
        } catch (IOException exception) {
            LOGGER.error("Failed to save fractal image", exception);
        }
    }

    /**
     * Подготавливает список аффинных преобразований.
     *
     * @param randomCount
     *            количество случайных преобразований
     * @param preset
     *            массив предустановленных преобразований
     *
     * @return список цветовых аффинных преобразований
     */
    private static List<ColorTransformation> prepareAffineTransformations(int randomCount,
            AffineTransformation[] preset) {
        List<ColorTransformation> affineTransformations = new ArrayList<>();

        // Генерация случайных аффинных преобразований
        for (int i = 0; i < randomCount; i++) {
            affineTransformations
                    .add(new ColorTransformation(LinearTransformation.randomTransformation(), Color.generate()));
        }

        // Добавление preset преобразований
        if (preset != null) {
            for (AffineTransformation presetObj : preset) {
                affineTransformations.add(new ColorTransformation(
                        new LinearTransformation(presetObj.a(), presetObj.b(), presetObj.c(), presetObj.d(),
                                presetObj.e(), presetObj.f()),
                        new Color(presetObj.red(), presetObj.green(), presetObj.blue())));
            }
        }

        return affineTransformations;
    }
}
