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
        // Создаём объект рендера с симметрией 0
        Renderer renderer = new SingleRenderer(0);

        // Создаём параметры для рендера
        var image = renderer.render(FractalImage.create(10, 10), // Холст для рендера
                new Rectangular(-1, -1, 2, 2), // Прямоугольная область
                List.of(new ColorTransformation(LinearTransformation.randomTransformation(),
                        // Случайное линейное преобразование
                        Color.generate() // Случайный цвет
                )), List.of(new HeartTransformation()), // Вариация "Сердце"
                20, // Количество сэмплов
                20, // Итерации на сэмпл
                0 // Начальное значение для генератора случайных чисел
        );

        // Проверяем, что изображение создано
        assertNotNull(image, "Rendered image should not be null");
    }
}
