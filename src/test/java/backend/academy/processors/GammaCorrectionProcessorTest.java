package backend.academy.processors;

import backend.academy.FractalFlame.components.Color;
import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Pixel;
import backend.academy.FractalFlame.components.SyncFractalImage;
import backend.academy.FractalFlame.processors.GammaCorrectionProcessor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GammaCorrectionProcessorTest {

    @Test
    public void process_shouldApplyGammaCorrection() {
        double gamma = 2.0;
        GammaCorrectionProcessor processor = new GammaCorrectionProcessor(gamma);
        IFractalImage image = createTestImage();

        int expectedRed = (int) (255 * Math.pow(image.pixel(0, 0).color().r() / 255.0, gamma));
        int expectedGreen = (int) (255 * Math.pow(image.pixel(0, 0).color().g() / 255.0, gamma));
        int expectedBlue = (int) (255 * Math.pow(image.pixel(0, 0).color().b() / 255.0, gamma));

        processor.process(image);

        Pixel processedPixel = image.pixel(0, 0);

        // Проверяем каждый цветовой канал с использованием стандартного JUnit
        assertEquals(expectedRed, processedPixel.color().r(), "Red channel should match expected value");
        assertEquals(expectedGreen, processedPixel.color().g(), "Green channel should match expected value");
        assertEquals(expectedBlue, processedPixel.color().b(), "Blue channel should match expected value");
    }

    private IFractalImage createTestImage() {
        SyncFractalImage image = SyncFractalImage.create(1, 1);
        image.updatePixel(0, 0, new Pixel(new Color(50, 100, 150), 1));
        return image;
    }
}
