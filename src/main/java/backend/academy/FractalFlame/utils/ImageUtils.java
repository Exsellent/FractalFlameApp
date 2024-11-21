package backend.academy.FractalFlame.utils;

import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Pixel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

/**
 * Утилитный класс для работы с изображениями.
 * <p>
 * Этот класс предоставляет методы для сохранения фрактальных изображений в различных форматах.
 * </p>
 *
 * @since 1.0
 */
@SuppressWarnings("MagicNumber")
public final class ImageUtils {

    /**
     * Приватный конструктор для предотвращения создания экземпляров класса.
     */
    private ImageUtils() {
    }

    /**
     * Сохраняет фрактальное изображение в указанный файл в заданном формате.
     *
     * @param image
     *            фрактальное изображение для сохранения
     * @param filename
     *            путь к файлу, в который будет сохранено изображение
     * @param format
     *            формат изображения
     *
     * @throws IOException
     *             если произошла ошибка ввода-вывода при сохранении изображения
     */
    public static void save(IFractalImage image, Path filename, ImageFormat format) throws IOException {
        BufferedImage bufferedImage = convertToBufferedImage(image);
        ImageIO.write(bufferedImage, format.toString(), filename.toFile());
    }

    /**
     * Конвертирует фрактальное изображение в {@link BufferedImage}.
     *
     * @param fractalImage
     *            фрактальное изображение для конвертации
     *
     * @return объект {@link BufferedImage}, представляющий фрактальное изображение
     */
    private static BufferedImage convertToBufferedImage(IFractalImage fractalImage) {
        BufferedImage bufferedImage = new BufferedImage(fractalImage.getWidth(),
            fractalImage.getHeight(),BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < fractalImage.getHeight(); y++) {
            for (int x = 0; x < fractalImage.getWidth(); x++) {
                Pixel pixel = fractalImage.pixel(x, y);
                int color = (pixel.color().r() << 16) | (pixel.color().g()
                    << 8) | pixel.color().b();
                bufferedImage.setRGB(x, y, color);
            }
        }

        return bufferedImage;
    }
}
