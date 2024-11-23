package backend.academy;

import backend.academy.FractalFlame.FractalFlameApp;
import backend.academy.FractalFlame.components.AffineTransformation;
import backend.academy.FractalFlame.config.Config;
import backend.academy.FractalFlame.transformations.NonLinearTransformations;
import backend.academy.FractalFlame.transformations.Transformation;
import backend.academy.FractalFlame.utils.ImageFormat;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FractalFlameAppTest {

    @TempDir
    Path tempDir;

    @Test
    void generateAndSaveFractal_withBasicConfig_shouldCreateFile() {
        Config config = new Config.Builder().setWidth(100).setHeight(100).setSamples(1).setIterations(1).setSeed(42)
                .setSymmetry(1).setThreadsCount(1).setGamma(2.2).setWithCorrection(true).setMinX(-1.0).setMaxX(1.0)
                .setMinY(-1.0).setMaxY(1.0).setRandomAffineTransformationsCount(2)
                .setNonlinearTransformations(
                        Arrays.stream(new NonLinearTransformations[] { NonLinearTransformations.DISC })
                                .map(NonLinearTransformations::getTransformation).toArray(Transformation[]::new))
                .setDirectory(tempDir.toString()).setFilename("test_fractal").setFileType(ImageFormat.PNG).build();

        FractalFlameApp.generateAndSaveFractal(config);

        Path expectedFile = tempDir.resolve("test_fractal.png");
        assertTrue(Files.exists(expectedFile), "Generated file should exist");
    }

    @Test
    void generateAndSaveFractal_withPresetTransformations_shouldCreateFile() {
        AffineTransformation[] presets = { new AffineTransformation(1, 0, 0, 0, 1, 0, 1, 0, 0),
                new AffineTransformation(0.5, 0, 0.5, 0, 0.5, 0.5, 0, 1, 0) };

        Config config = new Config.Builder().setWidth(100).setHeight(100).setSamples(1).setIterations(1).setSeed(42)
                .setSymmetry(1).setThreadsCount(1).setGamma(2.2).setWithCorrection(true).setMinX(-1.0).setMaxX(1.0)
                .setMinY(-1.0).setMaxY(1.0).setRandomAffineTransformationsCount(0)
                .setPresetAffineTransformations(presets)
                .setNonlinearTransformations(
                        Arrays.stream(new NonLinearTransformations[] { NonLinearTransformations.DISC })
                                .map(NonLinearTransformations::getTransformation).toArray(Transformation[]::new))
                .setDirectory(tempDir.toString()).setFilename("test_fractal_preset").setFileType(ImageFormat.PNG)
                .build();

        FractalFlameApp.generateAndSaveFractal(config);

        Path expectedFile = tempDir.resolve("test_fractal_preset.png");
        assertTrue(Files.exists(expectedFile), "Generated file should exist");
    }

    @Test
    void generateAndSaveFractal_withParallelRendering_shouldCreateFile() {
        Config config = new Config.Builder().setWidth(100).setHeight(100).setSamples(1).setIterations(1).setSeed(42)
                .setSymmetry(1).setThreadsCount(4).setGamma(2.2).setWithCorrection(true).setMinX(-1.0).setMaxX(1.0)
                .setMinY(-1.0).setMaxY(1.0).setRandomAffineTransformationsCount(2)
                .setNonlinearTransformations(
                        Arrays.stream(new NonLinearTransformations[] { NonLinearTransformations.DISC })
                                .map(NonLinearTransformations::getTransformation).toArray(Transformation[]::new))
                .setDirectory(tempDir.toString()).setFilename("test_fractal_parallel").setFileType(ImageFormat.PNG)
                .build();

        FractalFlameApp.generateAndSaveFractal(config);

        Path expectedFile = tempDir.resolve("test_fractal_parallel.png");
        assertTrue(Files.exists(expectedFile), "Generated file should exist");
    }

    @Test
    void generateAndSaveFractal_withInvalidPath_shouldNotThrowException() {
        Config config = new Config.Builder().setWidth(100).setHeight(100).setSamples(1).setIterations(1).setSeed(42)
                .setSymmetry(1).setThreadsCount(1).setGamma(2.2).setWithCorrection(true).setMinX(-1.0).setMaxX(1.0)
                .setMinY(-1.0).setMaxY(1.0).setRandomAffineTransformationsCount(1)
                .setNonlinearTransformations(
                        Arrays.stream(new NonLinearTransformations[] { NonLinearTransformations.DISC })
                                .map(NonLinearTransformations::getTransformation).toArray(Transformation[]::new))
                .setDirectory("/invalid/path/that/does/not/exist").setFilename("test_fractal")
                .setFileType(ImageFormat.PNG).build();

        assertDoesNotThrow(() -> FractalFlameApp.generateAndSaveFractal(config));
    }
}
