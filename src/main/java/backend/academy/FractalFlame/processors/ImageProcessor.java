package backend.academy.FractalFlame.processors;

import backend.academy.FractalFlame.components.IFractalImage;

@FunctionalInterface
public interface ImageProcessor {

    void process(IFractalImage image);
}
