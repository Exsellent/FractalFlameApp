package backend.academy.processors;

import backend.academy.FractalFlame.components.Color;
import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Pixel;
import backend.academy.FractalFlame.components.SyncFractalImage;
import backend.academy.FractalFlame.processors.LogarithmicGammaCorrectionProcessor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogarithmicGammaCorrectionProcessorTest {

    @Test
    public void process_shouldApplyLogarithmicGammaCorrection() {
        double gamma = 2.0;
        LogarithmicGammaCorrectionProcessor processor = new LogarithmicGammaCorrectionProcessor(gamma);
        IFractalImage image = createTestImage();

        Pixel originalPixel = image.pixel(0, 0);
        int expectedRed = calculateLogGammaCorrection(originalPixel.color().r(), gamma);
        int expectedGreen = calculateLogGammaCorrection(originalPixel.color().g(), gamma);
        int expectedBlue = calculateLogGammaCorrection(originalPixel.color().b(), gamma);

        processor.process(image);

        Pixel processedPixel = image.pixel(0, 0);

        // Check each color channel
        assertEquals(expectedRed, processedPixel.color().r(), "Red channel should match expected value");
        assertEquals(expectedGreen, processedPixel.color().g(), "Green channel should match expected value");
        assertEquals(expectedBlue, processedPixel.color().b(), "Blue channel should match expected value");
    }

    private IFractalImage createTestImage() {
        SyncFractalImage image = SyncFractalImage.create(1, 1);
        image.updatePixel(0, 0, new Pixel(new Color(50, 100, 150), 1));
        return image;
    }

    private int calculateLogGammaCorrection(int colorValue, double gamma) {
        double correctedValue = Math.max(colorValue, 1);
        double boarder = 255.0;
        double corr = 8.0;

        double logValue = boarder * Math.log(correctedValue) / Math.log(1 << (int) corr);
        double gammaCorrectedValue = Math.pow(logValue / boarder, gamma) * boarder;

        return (int) Math.min(boarder, Math.max(0, gammaCorrectedValue));
    }
}
