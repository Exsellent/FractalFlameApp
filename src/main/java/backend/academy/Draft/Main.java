package backend.academy.FractalFlame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    private Main() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void main(String[] args) {
        LOGGER.info("Starting Fractal Flame Generator");
        Launcher.launch();
    }
}
