package backend.academy.FractalFlame.components;

import java.util.Random;

/**
 * Представляет цвет RGB с красными, зелеными и синими компонентами. Каждый компонент представляет собой целое значение
 * от 0 до 255 включительно.
 */
public record Color(int r, int g, int b) {

    private static final int BOUND = 256;

    /**
     * Генерирует случайный цвет RGB.
     *
     * @return новый объект {@code Color} с каждым из его компонентов RGB, выбранным случайным образом в диапазоне от 0
     *         (включительно) до 256 (включительно).
     */
    public static Color generate() {
        Random random = new Random();
        int r = random.nextInt(BOUND);
        int g = random.nextInt(BOUND);
        int b = random.nextInt(BOUND);
        return new Color(r, g, b);
    }
}
