package backend.academy.FractalFlame.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Утилитарный класс для измерения времени выполнения задач.
 */
public final class PerformanceMeasurer {

    private static final Logger LOGGER = LogManager.getLogger(PerformanceMeasurer.class);
    private static final double NANOSECONDS_TO_SECONDS = 1_000_000_000.0;

    private PerformanceMeasurer() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Измеряет время выполнения переданной задачи.
     *
     * @param task
     *            задача для выполнения
     *
     * @return время выполнения в наносекундах
     */
    public static long measureExecutionTime(Runnable task) {
        long startTime = System.nanoTime();
        task.run();
        return System.nanoTime() - startTime;
    }

    /**
     * Логирует результаты производительности.
     *
     * @param rendererType
     *            тип рендера
     * @param duration
     *            время выполнения в наносекундах
     */
    public static void logPerformanceResults(String rendererType, long duration) {
        double durationInSeconds = duration / NANOSECONDS_TO_SECONDS;
        LOGGER.info("{} реализация заняла: {} секунд", rendererType, durationInSeconds);
    }
}
