package backend.academy.renderers;

import backend.academy.FractalFlame.components.Color;
import backend.academy.FractalFlame.components.Rectangular;
import backend.academy.FractalFlame.components.SyncFractalImage;
import backend.academy.FractalFlame.renderers.ParallelRenderer;
import backend.academy.FractalFlame.renderers.Renderer;
import backend.academy.FractalFlame.transformations.ColorTransformation;
import backend.academy.FractalFlame.transformations.HeartTransformation;
import backend.academy.FractalFlame.transformations.LinearTransformation;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParallelRendererTest {

    @Test
    void parallelRenderTest() {

        Renderer renderer = new ParallelRenderer(3, 0);

        var image = renderer.render(SyncFractalImage.create(10, 10),
                new Rectangular(-1, -1, 2, 2), // Rectangular area
                List.of(new ColorTransformation(LinearTransformation.randomTransformation(),

                    Color.generate())), List.of(new HeartTransformation()), 20, 20, 0
        );

        assertNotNull(image, "Rendered image should not be null");
    }
}
