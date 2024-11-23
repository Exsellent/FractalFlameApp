package backend.academy.components;

import backend.academy.FractalFlame.components.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ColorTest {

    @Test
    public void generate_shouldReturnColorWithRGBInRange() {
        int attempts = 1000; // Количество попыток генерации цвета
        for (int i = 0; i < attempts; i++) {
            Color color = Color.generate();

            assertTrue(color.r() >= 0 && color.r() <= 255, "Red component out of range");
            assertTrue(color.g() >= 0 && color.g() <= 255, "Green component out of range");
            assertTrue(color.b() >= 0 && color.b() <= 255, "Blue component out of range");
        }
    }
}
