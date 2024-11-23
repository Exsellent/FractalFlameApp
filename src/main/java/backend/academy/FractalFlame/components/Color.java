package backend.academy.FractalFlame.components;

import java.util.Random;

/**
 * Represents an RGB color with red, green, and blue components. Each component represents an integer value
 * from 0 to 255 inclusive.
 */
public record Color(int r, int g, int b) {

    private static final int BOUND = 256;

    /**
     * Generates a random RGB color.
     *
     * @return a new object {@code Color} with each of its RGB components randomly selected in the range from 0
     * (inclusive) to 256 (inclusive).
     */
    public static Color generate() {
        Random random = new Random();
        int r = random.nextInt(BOUND);
        int g = random.nextInt(BOUND);
        int b = random.nextInt(BOUND);
        return new Color(r, g, b);
    }
}
