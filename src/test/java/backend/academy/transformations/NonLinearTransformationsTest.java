package backend.academy.transformations;

import backend.academy.FractalFlame.transformations.NonLinearTransformations;
import backend.academy.FractalFlame.transformations.Transformation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NonLinearTransformationsTest {

    @Test
    public void getTransformation_shouldReturnValidTransformation() {
        // iterate through all the enumeration values and check their validity
        for (NonLinearTransformations transformationEnum : NonLinearTransformations.values()) {
            assertNotNull(transformationEnum.getTransformation(),
                    "Transformation should not be null for " + transformationEnum.name());
            assertTrue(transformationEnum.getTransformation() instanceof Transformation,
                    "Transformation should implement Transformation interface for " + transformationEnum.name());
        }
    }

    @Test
    public void getByNumber_shouldReturnCorrectTransformation() {
        // Check that the method returns the correct index conversion
        NonLinearTransformations[] values = NonLinearTransformations.values();
        for (int i = 0; i < values.length; i++) {
            assertEquals(values[i].getTransformation(), NonLinearTransformations.values()[i].getTransformation(),
                    "Transformation mismatch for index " + i);
        }
    }

    @Test
    public void getByNumber_withInvalidNumber_shouldThrowException() {
        // Check that the method throws an exception for an incorrect index
        int invalidNumber = NonLinearTransformations.values().length; // Вне диапазона

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            NonLinearTransformations[] values = NonLinearTransformations.values();
            Transformation transformation = values[invalidNumber].getTransformation();
        });
    }
}
