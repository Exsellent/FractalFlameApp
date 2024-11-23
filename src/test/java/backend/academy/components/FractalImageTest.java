package backend.academy.components;

import backend.academy.FractalFlame.components.Color;
import backend.academy.FractalFlame.components.FractalImage;
import backend.academy.FractalFlame.components.Pixel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FractalImageTest {

    @Test
    public void create_shouldInitializeAllPixelsToBlack() {
        int width = 10;
        int height = 10;
        FractalImage image = FractalImage.create(width, height);

        for (Pixel pixel : image.getData()) {
            assertEquals(new Color(0, 0, 0), pixel.color(), "Pixel color should be black");
            assertEquals(0, pixel.hitCount(), "Hit count should be zero");
        }
    }

    @Test
    public void contains_shouldReturnTrueForValidCoordinates() {
        FractalImage image = FractalImage.create(10, 10);
        assertTrue(image.contains(5, 5), "Should return true for valid coordinates");
    }

    @Test
    public void contains_shouldReturnFalseForInvalidCoordinates() {
        FractalImage image = FractalImage.create(10, 10);
        assertFalse(image.contains(10, 10), "Should return false for coordinates out of bounds");
    }

    @Test
    public void pixel_shouldReturnCorrectPixel() {
        FractalImage image = FractalImage.create(10, 10);
        Pixel newPixel = new Pixel(new Color(255, 255, 255), 1);
        image.updatePixel(5, 5, newPixel);

        Pixel pixel = image.pixel(5, 5);
        assertNotNull(pixel, "Pixel should not be null");
        assertEquals(new Color(255, 255, 255), pixel.color(), "Pixel color should match");
        assertEquals(1, pixel.hitCount(), "Hit count should match");
    }

    @Test
    public void pixel_shouldReturnNullForInvalidCoordinates() {
        FractalImage image = FractalImage.create(10, 10);
        assertNull(image.pixel(10, 10), "Should return null for invalid coordinates");
    }

    @Test
    public void updatePixel_shouldCorrectlyUpdatePixel() {
        FractalImage image = FractalImage.create(10, 10);
        Pixel newPixel = new Pixel(new Color(123, 45, 67), 2);
        image.updatePixel(5, 5, newPixel);

        Pixel updatedPixel = image.pixel(5, 5);
        assertNotNull(updatedPixel, "Updated pixel should not be null");
        assertEquals(new Color(123, 45, 67), updatedPixel.color(), "Updated pixel color should match");
        assertEquals(2, updatedPixel.hitCount(), "Updated pixel hit count should match");
    }
}
