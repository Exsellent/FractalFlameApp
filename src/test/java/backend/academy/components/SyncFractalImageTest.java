package backend.academy.components;

import backend.academy.FractalFlame.components.Color;
import backend.academy.FractalFlame.components.Pixel;
import backend.academy.FractalFlame.components.SyncFractalImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SyncFractalImageTest {

    @Test
    public void create_shouldInitializeAllPixelsToBlack() {
        SyncFractalImage image = SyncFractalImage.create(10, 10);

        for (Pixel pixel : image.getData()) {
            assertNotNull(pixel, "Pixel should not be null");
            assertEquals(new Color(0, 0, 0), pixel.color(), "Pixel color should be black");
            assertEquals(0, pixel.hitCount(), "Pixel hit count should be zero");
        }
    }

    @Test
    public void pixelAndUpdatePixel_shouldBeThreadSafe() throws InterruptedException {
        SyncFractalImage image = SyncFractalImage.create(10, 10);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable updateTask = () -> {
            for (int i = 0; i < 1000; i++) {
                image.updatePixel(5, 5, new Pixel(new Color(i % 256, i % 256, i % 256), i));
            }
        };

        executor.execute(updateTask);
        executor.execute(updateTask);

        executor.shutdown();
        assertTrue(executor.awaitTermination(1, TimeUnit.SECONDS), "Executor should terminate within 1 second");

        Pixel finalPixel = image.pixel(5, 5);
        assertNotNull(finalPixel, "Final pixel should not be null");
        assertTrue(finalPixel.hitCount() >= 0 && finalPixel.hitCount() < 2000,
                "Hit count should be between 0 and 1999");
    }

    @Test
    public void contains_shouldReturnTrueForValidCoordinates() {
        SyncFractalImage image = SyncFractalImage.create(10, 10);
        assertTrue(image.contains(5, 5), "Contains should return true for valid coordinates");
    }

    @Test
    public void contains_shouldReturnFalseForInvalidCoordinates() {
        SyncFractalImage image = SyncFractalImage.create(10, 10);
        assertFalse(image.contains(10, 10), "Contains should return false for out-of-bounds coordinates");
    }
}
