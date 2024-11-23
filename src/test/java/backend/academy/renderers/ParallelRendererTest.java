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
        // Создаём объект рендера с 3 потоками и без симметрии
        Renderer renderer = new ParallelRenderer(3, 0);

        // Создаём параметры для рендера
        var image = renderer.render(SyncFractalImage.create(10, 10), // Холст для рендера
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
