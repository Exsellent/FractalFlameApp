package backend.academy.FractalFlame.renderers;

import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Point;
import backend.academy.FractalFlame.components.Rectangular;
import backend.academy.FractalFlame.transformations.ColorTransformation;
import backend.academy.FractalFlame.transformations.Transformation;
import java.util.List;
import java.util.Random;

public class SingleRenderer extends AbstractRenderer {

    private final int symmetry;

    public SingleRenderer(int symmetry) {
        this.symmetry = symmetry;
    }

    @Override
    public IFractalImage render(IFractalImage canvas, Rectangular world, List<ColorTransformation> affine,
            List<Transformation> variations, int samples, int iterPerSample, int seed) {
        Random random = new Random(seed);

        for (int i = 0; i < samples; i++) {
            Point pw = randomPoint(world, random);

            for (int step = 0; step < iterPerSample; ++step) {
                ColorTransformation chosenAffine = affine.get(random.nextInt(affine.size()));
                Transformation variation = variations.get(random.nextInt(variations.size()));

                pw = variation.apply(chosenAffine.transformation().apply(pw));

                if (symmetry > 0) {
                    for (int s = 0; s < symmetry; s++) {
                        double theta = Math.PI * 2 / symmetry * s;
                        Point pwr = rotate(pw, theta);

                        applyChanges(canvas, world, pwr, chosenAffine);
                    }
                } else {
                    applyChanges(canvas, world, pw, chosenAffine);
                }
            }
        }

        return canvas;
    }

    private Point randomPoint(Rectangular world, Random random) {
        double x = world.x() + (random.nextDouble() * world.width());
        double y = world.y() + (random.nextDouble() * world.height());
        return new Point(x, y);
    }
}
