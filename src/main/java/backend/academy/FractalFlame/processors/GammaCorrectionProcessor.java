package backend.academy.FractalFlame.processors;

import backend.academy.FractalFlame.components.Color;
import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Pixel;

public class GammaCorrectionProcessor implements ImageProcessor {

    private final double gamma;
    private static final int BORDER = 255;

    public GammaCorrectionProcessor(double gamma) {
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
        int r = (int) (BORDER * Math.pow((double) pixel.color().r() / BORDER, gamma));
        int g = (int) (BORDER * Math.pow((double) pixel.color().g() / BORDER, gamma));
        int b = (int) (BORDER * Math.pow((double) pixel.color().b() / BORDER, gamma));

        return new Pixel(new Color(r, g, b), pixel.hitCount());
    }
}
