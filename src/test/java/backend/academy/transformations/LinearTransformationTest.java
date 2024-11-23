package backend.academy.transformations;

import backend.academy.FractalFlame.components.Point;
import backend.academy.FractalFlame.transformations.LinearTransformation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LinearTransformationTest {

    @Test
    public void apply_shouldTransformPointCorrectly() {
        // Linear transformation coefficients
        double a = 1, b = 0, c = 1, d = 0, e = 1, f = 1;
        LinearTransformation transformation = new LinearTransformation(a, b, c, d, e, f);

        // Starting point
        Point originalPoint = new Point(2, 3);

        // Applying the transformation
        Point result = transformation.apply(originalPoint);

        // Checking the coordinates with an acceptable margin of error
        assertEquals(3.0, result.x(), 1e-10, "X coordinate should match");
        assertEquals(4.0, result.y(), 1e-10, "Y coordinate should match");
    }

    @Test
    public void randomTransformation_shouldCreateTransformation() {
        LinearTransformation transformation = LinearTransformation.randomTransformation();
        assertNotNull(transformation, "Randomly generated transformation should not be null");
    }
}
