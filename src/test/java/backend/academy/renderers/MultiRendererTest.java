package backend.academy.renderers;

import backend.academy.FractalFlame.components.Color;
import backend.academy.FractalFlame.components.FractalImage;
import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Rectangular;
import backend.academy.FractalFlame.renderers.MultiRenderer;
import backend.academy.FractalFlame.renderers.Renderer;
import backend.academy.FractalFlame.transformations.ColorTransformation;
import backend.academy.FractalFlame.transformations.HeartTransformation;
import backend.academy.FractalFlame.transformations.LinearTransformation;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MultiRendererTest {

    @Test
    void multiRenderTest() {
        // Creating a render object with 3 threads and no symmetry
        Renderer renderer = new MultiRenderer(3, 0);

        // Creating parameters for the render
        IFractalImage image = renderer.render(FractalImage.create(10, 10), // Canvas for rendering
                new Rectangular(-1, -1, 2, 2),
                List.of(new ColorTransformation(LinearTransformation.randomTransformation(),
                    // Random linear transformation
                        Color.generate() // Random color
                )), List.of(new HeartTransformation()), // Variation of "Heart"
                20, // Number of samples
                20, // Iterations per sample
                0 // Initial value for the random number generator
        );

        // Check that the image has been created
        assertNotNull(image, "Rendered image should not be null");
    }
}
