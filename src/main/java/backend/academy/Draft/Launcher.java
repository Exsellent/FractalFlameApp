package backend.academy.FractalFlame;

import backend.academy.FractalFlame.components.AffineTransformation;
import backend.academy.FractalFlame.components.Color;
import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Rectangular;
import backend.academy.FractalFlame.components.SyncFractalImage;
import backend.academy.FractalFlame.config.Config;
import backend.academy.FractalFlame.renderers.ParallelRenderer;
import backend.academy.FractalFlame.renderers.Renderer;
import backend.academy.FractalFlame.renderers.SingleRenderer;
import backend.academy.FractalFlame.transformations.ColorTransformation;
import backend.academy.FractalFlame.transformations.LinearTransformation;
import backend.academy.FractalFlame.transformations.NonLinearTransformations;
import backend.academy.FractalFlame.transformations.Transformation;
import backend.academy.FractalFlame.utils.ImageFormat;
import backend.academy.FractalFlame.utils.ImageUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Launcher {
    private static final Logger LOGGER = LogManager.getLogger(Launcher.class);
    private static final String CURRENT_DIRECTORY = "current";

    private Launcher() {
        throw new UnsupportedOperationException("Utility class");
    }

    @SuppressWarnings("MagicNumber")
    public static void launch() {
        Config.Builder configBuilder = new Config.Builder();

        try (Scanner scanner = new Scanner(System.in)) {
            LOGGER.info("Welcome to Fractal Flame Generator!");

            // Настройка конфигурации через ввод пользователя
            configBuilder = getFractalFlameConfigFromUser(scanner, configBuilder);

            LOGGER.info("Enter the output file name (JPEG, BMP, PNG):");
            String outputFilename = scanner.nextLine().trim();
            ImageFormat format = determineFormat(outputFilename);

            // Настройка предопределённых аффинных преобразований
            if (configBuilder.build().getPresetAffineTransformations() == null) {
                AffineTransformation[] defaultTransformations = {
                        new AffineTransformation(0.5, 0, 0, 0, 0.5, 0, 255, 0, 0),
                        new AffineTransformation(0.5, 0, 0.5, 0, 0.5, 0, 0, 255, 0),
                        new AffineTransformation(0.5, 0, 0, 0, 0.5, 0.5, 0, 0, 255) };
                configBuilder.setPresetAffineTransformations(defaultTransformations);
            }

            // Выбор нелинейных преобразований
            List<String> selectedTransformations = selectTransformations(scanner);
            Transformation[] transformationsArray = selectedTransformations.stream().map(transformationName -> {
                try {
                    return NonLinearTransformations.valueOf(transformationName).getTransformation();
                } catch (IllegalArgumentException e) {
                    LOGGER.warn("Unknown transformation: {}. Using SINUSOIDAL instead.", transformationName);
                    return NonLinearTransformations.SPIRAL.getTransformation();
                }
            }).toArray(Transformation[]::new);

            configBuilder.setNonlinearTransformations(transformationsArray);
            Config config = configBuilder.build();

            // Создание рендера на основе конфигурации
            Renderer renderer = config.getThreadsCount() > 1
                    ? new ParallelRenderer(config.getThreadsCount(), config.getSymmetry())
                    : new SingleRenderer(config.getSymmetry());

            // Рендеринг изображения
            IFractalImage image = renderer.render(SyncFractalImage.create(config.getWidth(), config.getHeight()),
                    new Rectangular(config.getMinX(), config.getMinY(), config.getMaxX() - config.getMinX(),
                            config.getMaxY() - config.getMinY()),
                    prepareAffineTransformations(config), List.of(transformationsArray), config.getSamples(),
                    config.getIterations(), config.getSeed());

            // Сохранение изображения
            String directory = config.getDirectory().equals(CURRENT_DIRECTORY) ? "." : config.getDirectory();
            ImageUtils.save(image, Path.of(directory, outputFilename), format);
            LOGGER.info("Fractal saved successfully at {}", outputFilename);
        } catch (IOException e) {
            LOGGER.error("Failed to save the fractal image: ", e);
        } catch (Exception e) {
            LOGGER.error("An unexpected error occurred: ", e);
        }
    }

    private static List<String> selectTransformations(Scanner scanner) {
        List<String> selectedTransformations = new ArrayList<>();
        LOGGER.info("Available classic transformations:");
        for (NonLinearTransformations t : NonLinearTransformations.values()) {
            LOGGER.info("- " + t.name());
        }
        LOGGER.info("Enter transformation name (or press Enter for FLOWER):");
        String input = scanner.nextLine().trim().toUpperCase();
        selectedTransformations.add(input.isEmpty() ? "FLOWER" : input);

        return selectedTransformations;
    }

    @SuppressWarnings("MagicNumber")
    private static Config.Builder getFractalFlameConfigFromUser(Scanner scanner, Config.Builder builder) {
        LOGGER.info("Image width (default 800):");
        builder.setWidth(getIntInput(scanner, 800));

        LOGGER.info("Image height (default 600):");
        builder.setHeight(getIntInput(scanner, 600));

        LOGGER.info("Number of samples (default 1,000,000):");
        builder.setSamples(getIntInput(scanner, 1_000_000));

        LOGGER.info("Iterations per sample (default 100):");
        builder.setIterations(getIntInput(scanner, 100));

        LOGGER.info("Enter symmetry level (default 0):");
        builder.setSymmetry(getIntInput(scanner, 0));

        LOGGER.info("Enter number of threads (default 1 for single-thread):");
        builder.setThreadsCount(getIntInput(scanner, 1));

        LOGGER.info("Enter output directory (default current):");
        String directoryInput = scanner.nextLine().trim();
        builder.setDirectory(directoryInput.isEmpty() ? CURRENT_DIRECTORY : directoryInput);

        return builder;
    }

    private static int getIntInput(Scanner scanner, int defaultValue) {
        try {
            String input = scanner.nextLine().trim();
            return input.isEmpty() ? defaultValue : Integer.parseInt(input);
        } catch (NumberFormatException e) {
            LOGGER.warn("Invalid input, using default value: {}", defaultValue);
            return defaultValue;
        }
    }

    private static List<ColorTransformation> prepareAffineTransformations(Config config) {
        List<ColorTransformation> transformations = new ArrayList<>();
        for (AffineTransformation t : config.getPresetAffineTransformations()) {
            transformations
                    .add(new ColorTransformation(new LinearTransformation(t.a(), t.b(), t.c(), t.d(), t.e(), t.f()),
                            new Color(t.red(), t.green(), t.blue())));
        }
        return transformations;
    }

    private static ImageFormat determineFormat(String filename) {
        String extension = filename.substring(filename.lastIndexOf('.') + 1).toUpperCase();
        return ImageFormat.valueOf(extension);
    }
}
