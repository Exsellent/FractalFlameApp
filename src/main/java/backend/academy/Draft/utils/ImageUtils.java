package backend.academy.FractalFlame.utils;

import backend.academy.FractalFlame.components.IFractalImage;
import backend.academy.FractalFlame.components.Pixel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

@SuppressWarnings("MagicNumber")
public final class ImageUtils {

    private ImageUtils() {
    }

    public static void save(IFractalImage image, Path filename, ImageFormat format) throws IOException {
        BufferedImage bufferedImage = convertToBufferedImage(image);
        ImageIO.write(bufferedImage, format.toString(), filename.toFile());
    }

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
