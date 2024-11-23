package backend.academy.FractalFlame.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс для логирования результатов производительности в файл.
 */
public final class PerformanceLogger {

    private static final Logger LOGGER = LogManager.getLogger(PerformanceLogger.class);
    private static final String FILE_NAME = "results.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final double NANOSECONDS_TO_SECONDS = 1_000_000_000.0;
    private static final String SEPARATOR = "----------------------------------\n";

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
     */
    public static void logResults(long durationSingle, long durationParallel) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("Результаты производительности\n").append(SEPARATOR).append("Дата и время: ")
                .append(LocalDateTime.now().format(DATE_FORMAT)).append("\n")
                .append(String.format("Однопоточная реализация заняла: %.3f секунд%n",
                        durationSingle / NANOSECONDS_TO_SECONDS))
                .append(String.format("Многопоточная реализация заняла: %.3f секунд%n",
                        durationParallel / NANOSECONDS_TO_SECONDS));

        if (durationParallel < durationSingle) {
            logMessage.append(String.format("Многопоточная реализация быстрее на %.3f секунд%n",
                    (durationSingle - durationParallel) / NANOSECONDS_TO_SECONDS));
        } else {
            logMessage.append(String.format("Однопоточная реализация быстрее на %.3f секунд%n",
                    (durationParallel - durationSingle) / NANOSECONDS_TO_SECONDS));
        }

        logMessage.append(SEPARATOR);
        writeToFile(logMessage.toString());
    }

    /**
     * Записывает лог в файл.
     *
     * @param message
     *            Текст для записи.
     */
    private static void writeToFile(String message) {
        try {
            Path logFilePath = Path.of(FILE_NAME);
            if (!Files.exists(logFilePath)) {
                Files.createFile(logFilePath);
            }

            // Указываем кодировку UTF-8
            try (FileWriter writer = new FileWriter(logFilePath.toFile(), StandardCharsets.UTF_8, true)) {
                writer.write(message);
            }
        } catch (IOException e) {
            LOGGER.error("Ошибка записи в лог файл: {}", e.getMessage(), e);
        }
    }
}
