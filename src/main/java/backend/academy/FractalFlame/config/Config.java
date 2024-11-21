package backend.academy.FractalFlame.config;

import backend.academy.FractalFlame.components.AffineTransformation;
import backend.academy.FractalFlame.transformations.Transformation;
import backend.academy.FractalFlame.utils.ImageFormat;

public class Config {

    private static final String DIRECTORY_ERROR = "Directory cannot be null or blank.";
    private static final String FILENAME_ERROR = "Filename cannot be null or blank.";

    private final int width;
    private final int height;
    private final double minX;
    private final double maxX;
    private final double minY;
    private final double maxY;
    private final int threadsCount;
    private final int symmetry;
    private final int samples;
    private final int iterations;
    private final int randomAffineTransformationsCount;
    private final double gamma;
    private final int seed;
    private final boolean withCorrection;
    private final ImageFormat fileType;
    private final String directory;
    private final String filename;
    private final AffineTransformation[] presetAffineTransformations;
    private final Transformation[] nonlinearTransformations;

    @SuppressWarnings("MagicNumber")
    private Config(Builder builder) {
        this.width = builder.width;
        this.height = builder.height;
        this.minX = builder.minX;
        this.maxX = builder.maxX;
        this.minY = builder.minY;
        this.maxY = builder.maxY;
        this.threadsCount = builder.threadsCount;
        this.symmetry = builder.symmetry;
        this.samples = builder.samples;
        this.iterations = builder.iterations;
        this.randomAffineTransformationsCount = builder.randomAffineTransformationsCount;
        this.gamma = builder.gamma;
        this.seed = builder.seed;
        this.withCorrection = builder.withCorrection;
        this.fileType = builder.fileType;
        this.directory = builder.directory;
        this.filename = builder.filename;
        this.presetAffineTransformations = builder.presetAffineTransformations;
        this.nonlinearTransformations = builder.nonlinearTransformations;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public int getThreadsCount() {
        return threadsCount;
    }

    public int getSymmetry() {
        return symmetry;
    }

    public int getSamples() {
        return samples;
    }

    public int getIterations() {
        return iterations;
    }

    public int getRandomAffineTransformationsCount() {
        return randomAffineTransformationsCount;
    }

    public double getGamma() {
        return gamma;
    }

    public int getSeed() {
        return seed;
    }

    public boolean isWithCorrection() {
        return withCorrection;
    }

    public ImageFormat getFileType() {
        return fileType;
    }

    public String getDirectory() {
        return directory;
    }

    public String getFilename() {
        return filename;
    }

    public AffineTransformation[] getPresetAffineTransformations() {
        return presetAffineTransformations;
    }

    public Transformation[] getNonlinearTransformations() {
        return nonlinearTransformations;
    }

    @SuppressWarnings("MagicNumber")
    public static class Builder {

        private int width = 800;
        private int height = 600;
        private double minX = -2.0;
        private double maxX = 2.0;
        private double minY = -1.5;
        private double maxY = 1.5;
        private int threadsCount = 1;
        private int symmetry = 0;
        private int samples = 1_000_000;
        private int iterations = 100;
        private int randomAffineTransformationsCount = 0;
        private double gamma = 1.0;
        private int seed = 42;
        private boolean withCorrection = false;
        private ImageFormat fileType = ImageFormat.PNG;
        private String directory = ".";
        private String filename = "fractal";
        private AffineTransformation[] presetAffineTransformations = null;
        private Transformation[] nonlinearTransformations = null;

        // Методы для задания значений
        public Builder setWidth(int width) {
            if (width <= 0) {
                throw new IllegalArgumentException("Width must be positive.");
            }
            this.width = width;
            return this;
        }

        public Builder setHeight(int height) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height must be positive.");
            }
            this.height = height;
            return this;
        }

        public Builder setMinX(double minX) {
            this.minX = minX;
            return this;
        }

        public Builder setMaxX(double maxX) {
            this.maxX = maxX;
            return this;
        }

        public Builder setMinY(double minY) {
            this.minY = minY;
            return this;
        }

        public Builder setMaxY(double maxY) {
            this.maxY = maxY;
            return this;
        }

        public Builder setThreadsCount(int threadsCount) {
            if (threadsCount <= 0) {
                throw new IllegalArgumentException("Threads count must be positive.");
            }
            this.threadsCount = threadsCount;
            return this;
        }

        public Builder setSymmetry(int symmetry) {
            this.symmetry = symmetry;
            return this;
        }

        public Builder setSamples(int samples) {
            if (samples <= 0) {
                throw new IllegalArgumentException("Samples must be positive.");
            }
            this.samples = samples;
            return this;
        }

        public Builder setIterations(int iterations) {
            if (iterations <= 0) {
                throw new IllegalArgumentException("Iterations must be positive.");
            }
            this.iterations = iterations;
            return this;
        }

        public Builder setRandomAffineTransformationsCount(int count) {
            if (count < 0) {
                throw new IllegalArgumentException("Random affine transformations count cannot be negative.");
            }
            this.randomAffineTransformationsCount = count;
            return this;
        }

        public Builder setGamma(double gamma) {
            if (gamma <= 0) {
                throw new IllegalArgumentException("Gamma must be positive.");
            }
            this.gamma = gamma;
            return this;
        }

        public Builder setSeed(int seed) {
            this.seed = seed;
            return this;
        }

        public Builder setWithCorrection(boolean withCorrection) {
            this.withCorrection = withCorrection;
            return this;
        }

        public Builder setFileType(ImageFormat fileType) {
            this.fileType = fileType;
            return this;
        }

        @SuppressWarnings("MagicNumber")
        public Builder setDirectory(String directory) {
            if (directory == null || directory.isBlank()) {
                throw new IllegalArgumentException(DIRECTORY_ERROR);
            }
            this.directory = directory;
            return this;
        }

        @SuppressWarnings("MagicNumber")
        public Builder setFilename(String filename) {
            if (filename == null || filename.isBlank()) {
                throw new IllegalArgumentException(FILENAME_ERROR);
            }
            this.filename = filename;
            return this;
        }

        public Builder setPresetAffineTransformations(AffineTransformation[] transformations) {
            this.presetAffineTransformations = transformations;
            return this;
        }

        public Builder setNonlinearTransformations(Transformation[] transformations) {
            this.nonlinearTransformations = transformations;
            return this;
        }

        // Валидация параметров и создание объекта Config
        public Config build() {
            validateConfig();
            return new Config(this);
        }

        private void validateConfig() {
            if (width <= 0 || height <= 0) {
                throw new IllegalArgumentException("Width and height must be positive.");
            }
            if (samples <= 0 || iterations <= 0) {
                throw new IllegalArgumentException("Samples and iterations must be positive.");
            }
            if (directory == null || directory.isBlank()) {
                throw new IllegalArgumentException(DIRECTORY_ERROR);
            }
            if (filename == null || filename.isBlank()) {
                throw new IllegalArgumentException(FILENAME_ERROR);
            }
        }
    }
}
