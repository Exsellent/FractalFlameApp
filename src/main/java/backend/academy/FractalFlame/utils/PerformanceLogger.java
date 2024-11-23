package backend.academy.FractalFlame.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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
     * Logs the performance results to a file.
     *
     * @param durationSingle
     *      Execution time of a single-threaded implementation (in nanoseconds)
     * @param durationParallel
     *      Execution time of multithreaded implementation (in nanoseconds)
     * @param speedup
     *      Acceleration
     */
    public static void logResults(double durationSingle, double durationParallel, double speedup) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("The single-threaded implementation took: ").append(String.format(TIME_FORMAT, durationSingle))
                .append(SECONDS_SUFFIX).append("The multithreaded implementation took: ")
                .append(String.format(TIME_FORMAT, durationParallel)).append(SECONDS_SUFFIX).append("Ускорение: ")
                .append(String.format(SPEEDUP_FORMAT, speedup)).append("x\n");

        LOGGER.info("The single-threaded implementation took: {} секунд", String.format(TIME_FORMAT, durationSingle));
        LOGGER.info("The multithreaded implementation took: {} секунд", String.format(TIME_FORMAT, durationParallel));
        LOGGER.info("Boost: {}x", String.format(SPEEDUP_FORMAT, speedup));

        writeToFile(logMessage.toString());
    }

    /**
     * Writes the log to a file.
     *
     * @param message
     *      Text to record.
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
            LOGGER.error("Error writing to the log file: {}", e.getMessage(), e);
        }
    }
}
