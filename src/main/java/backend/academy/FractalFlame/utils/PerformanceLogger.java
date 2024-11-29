package backend.academy.FractalFlame.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс для логирования результатов производительности в файл.
 */
public final class PerformanceLogger {

    private static final Logger LOGGER = LogManager.getLogger(PerformanceLogger.class);
    private static final String FILE_NAME = "performance_results.txt";
    private static final double NANOSECONDS_TO_SECONDS = 1_000_000_000.0;
    private static final String TIME_FORMAT = "%.3f";
    private static final String SPEEDUP_FORMAT = "%.2f";
    private static final String SECONDS_SUFFIX = " секунд\n";

    private PerformanceLogger() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Логирует результаты производительности в файл.
     *
     * @param durationSingle
     *            Время выполнения однопоточной реализации (в наносекундах)
     * @param durationParallel
     *            Время выполнения многопоточной реализации (в наносекундах)
     * @param speedup
     *            Ускорение
     */

    public static void logResults(double durationSingle, double durationParallel, double speedup) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("Однопоточная реализация заняла: ").append(String.format(TIME_FORMAT, durationSingle))
                .append(SECONDS_SUFFIX).append("Многопоточная реализация заняла: ")
                .append(String.format(TIME_FORMAT, durationParallel)).append(SECONDS_SUFFIX).append("Ускорение: ")
                .append(String.format(SPEEDUP_FORMAT, speedup)).append("x\n");

        LOGGER.info("Однопоточная реализация заняла: {} секунд", String.format(TIME_FORMAT, durationSingle));
        LOGGER.info("Многопоточная реализация заняла: {} секунд", String.format(TIME_FORMAT, durationParallel));
        LOGGER.info("Ускорение: {}x", String.format(SPEEDUP_FORMAT, speedup));

        writeToFile(logMessage.toString());
    }

    /**
     * Записывает лог в файл.
     *
     * @param message
     *            Текст для записи.
     */
    private static void writeToFile(String message) {
        Path logFilePath = Path.of(FILE_NAME);

        try {
            if (!Files.exists(logFilePath)) {
                Files.createFile(logFilePath);
            }

            try (FileWriter writer = new FileWriter(logFilePath.toFile(), StandardCharsets.UTF_8, true)) {
                writer.write(message);
            }
        } catch (IOException e) {
            LOGGER.error("Ошибка записи в лог файл: {}", e.getMessage(), e);
        }
    }
}
