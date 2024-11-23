package backend.academy.FractalFlame.utils;

import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Pixel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

/**
 * A utility class for working with images.
 * <p>
 * This class provides methods for saving fractal images in various formats.
 * </p>
 *
 * @since 1.0
 */
@SuppressWarnings("MagicNumber")
public final class ImageUtils {

    private ImageUtils() {
    }

    /**
     * Saves the fractal image to the specified file in the specified format.
     *
     * @param image
     *      Fractal image to save
     * @param filename
     *      the path to the file where the image will be saved
     * @param format
     *      Image format
     *
     * @throws IOException
     *      if an I/O error occurred while saving the image
     */
    public static void save(IFractalImage image, Path filename, ImageFormat format) throws IOException {
        BufferedImage bufferedImage = convertToBufferedImage(image);
        ImageIO.write(bufferedImage, format.toString(), filename.toFile());
    }

    /**
     * Converts a fractal image to {@link BufferedImage}.
     *
     * @param fractalImage
     *      fractal image for conversion
     *
     * @return object {@link BufferedImage} representing a fractal image
     */
    private static BufferedImage convertToBufferedImage(IFractalImage fractalImage) {
        BufferedImage bufferedImage = new BufferedImage(fractalImage.getWidth(), fractalImage.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < fractalImage.getHeight(); y++) {
            for (int x = 0; x < fractalImage.getWidth(); x++) {
                Pixel pixel = fractalImage.pixel(x, y);
                int color = (pixel.color().r() << 16) | (pixel.color().g() << 8) | pixel.color().b();
                bufferedImage.setRGB(x, y, color);
            }
        }

        return bufferedImage;
    }
}
