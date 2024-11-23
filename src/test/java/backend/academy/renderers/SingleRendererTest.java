package backend.academy.renderers;

import backend.academy.FractalFlame.components.Color;
import backend.academy.FractalFlame.components.FractalImage;
import backend.academy.FractalFlame.components.Rectangular;
import backend.academy.FractalFlame.renderers.Renderer;
import backend.academy.FractalFlame.renderers.SingleRenderer;
import backend.academy.FractalFlame.transformations.ColorTransformation;
import backend.academy.FractalFlame.transformations.HeartTransformation;
import backend.academy.FractalFlame.transformations.LinearTransformation;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SingleRendererTest {

    @Test
    void singleRenderTest() {

        Renderer renderer = new SingleRenderer(0);

        var image = renderer.render(FractalImage.create(10, 10),
                new Rectangular(-1, -1, 2, 2),
                List.of(new ColorTransformation(LinearTransformation.randomTransformation(),

                    Color.generate())), List.of(new HeartTransformation()), 20,
                20, // Итерации на сэмпл
            0
        );

        assertNotNull(image, "Rendered image should not be null");
    }
}
