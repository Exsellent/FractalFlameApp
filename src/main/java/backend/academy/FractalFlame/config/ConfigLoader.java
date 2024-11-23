package backend.academy.FractalFlame.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public final class ConfigLoader {

    private ConfigLoader() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Loads the configuration from the specified file.
     *
     * @param filePath
     *      path to the configuration file
     *
     * @return object {@link Config}
     *
     * @throws IOException
     *      if a file reading error has occurred
     */
    public static Config loadConfig(String filePath) throws IOException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        }

        Config.Builder builder = new Config.Builder();
        builder.setWidth(Integer.parseInt(properties.getProperty("width", "800")));
        builder.setHeight(Integer.parseInt(properties.getProperty("height", "600")));
        builder.setSamples(Integer.parseInt(properties.getProperty("samples", "1000000")));
        builder.setIterations(Integer.parseInt(properties.getProperty("iterations", "100")));
        builder.setThreadsCount(Integer.parseInt(properties.getProperty("threads", "1")));
        builder.setSymmetry(Integer.parseInt(properties.getProperty("symmetry", "0")));
        builder.setDirectory(properties.getProperty("outputDirectory", "."));
        builder.setFilename(properties.getProperty("filename", "fractal"));

        return builder.build();
    }
}
