package backend.academy.FractalFlame.processors;

import backend.academy.FractalFlame.components.Color;
import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Pixel;

public class LogarithmicGammaCorrectionProcessor implements ImageProcessor {

    private final double gamma;
    private static final int BOARDER = 255;
    private static final int CORR = 8;

    public LogarithmicGammaCorrectionProcessor(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public void process(IFractalImage image) {
        for (int i = 0; i < image.getData().length; i++) {
            Pixel pixel = image.getData()[i];
            image.getData()[i] = applyGammaCorrection(pixel);
        }
    }

    private Pixel applyGammaCorrection(Pixel pixel) {
        double correctedR = Math.max(pixel.color().r(), 1);
        double correctedG = Math.max(pixel.color().g(), 1);
        double correctedB = Math.max(pixel.color().b(), 1);

        double newR = BOARDER * Math.log(correctedR) / Math.log(1 << CORR);
        double newG = BOARDER * Math.log(correctedG) / Math.log(1 << CORR);
        double newB = BOARDER * Math.log(correctedB) / Math.log(1 << CORR);

        newR = Math.pow(newR / BOARDER, gamma) * BOARDER;
        newG = Math.pow(newG / BOARDER, gamma) * BOARDER;
        newB = Math.pow(newB / BOARDER, gamma) * BOARDER;

        int finalR = (int) Math.min(BOARDER, Math.max(0, newR));
        int finalG = (int) Math.min(BOARDER, Math.max(0, newG));
        int finalB = (int) Math.min(BOARDER, Math.max(0, newB));

        return new Pixel(new Color(finalR, finalG, finalB), pixel.hitCount());
    }
}
