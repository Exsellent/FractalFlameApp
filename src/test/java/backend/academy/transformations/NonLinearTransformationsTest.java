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
        // Перебираем все значения перечисления и проверяем их валидность
        for (NonLinearTransformations transformationEnum : NonLinearTransformations.values()) {
            assertNotNull(transformationEnum.getTransformation(),
                    "Transformation should not be null for " + transformationEnum.name());
            assertTrue(transformationEnum.getTransformation() instanceof Transformation,
                    "Transformation should implement Transformation interface for " + transformationEnum.name());
        }
    }

    @Test
    public void getByNumber_shouldReturnCorrectTransformation() {
        // Проверяем, что метод возвращает правильное преобразование по индексу
        NonLinearTransformations[] values = NonLinearTransformations.values();
        for (int i = 0; i < values.length; i++) {
            assertEquals(values[i].getTransformation(), NonLinearTransformations.values()[i].getTransformation(),
                    "Transformation mismatch for index " + i);
        }
    }

    @Test
    public void getByNumber_withInvalidNumber_shouldThrowException() {
        // Проверяем, что метод выбрасывает исключение для некорректного индекса
        int invalidNumber = NonLinearTransformations.values().length; // Вне диапазона

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            NonLinearTransformations[] values = NonLinearTransformations.values();
            Transformation transformation = values[invalidNumber].getTransformation();
        });
    }
}
