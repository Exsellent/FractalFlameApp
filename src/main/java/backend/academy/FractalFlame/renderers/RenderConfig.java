package backend.academy.FractalFlame.renderers;

import backend.academy.FractalFlame.components.Rectangular;
import backend.academy.FractalFlame.transformations.ColorTransformation;
import backend.academy.FractalFlame.transformations.Transformation;
import java.util.List;

/**
 * Конфигурация для тестирования производительности рендеров.
 */
public class RenderConfig {
    private final int width;
    private final int height;
    private final Rectangular world;
    private final List<ColorTransformation> affine;
    private final List<Transformation> variations;
    private final int symmetry;
    private final int threadCount;
    private final int samples;
    private final int iterPerSample;
    private final int seed;

    private RenderConfig(Builder builder) {
        this.width = builder.width;
        this.height = builder.height;
        this.world = builder.world;
        this.affine = builder.affine;
        this.variations = builder.variations;
        this.symmetry = builder.symmetry;
        this.threadCount = builder.threadCount;
        this.samples = builder.samples;
        this.iterPerSample = builder.iterPerSample;
        this.seed = builder.seed;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangular getWorld() {
        return world;
    }

    public List<ColorTransformation> getAffine() {
        return affine;
    }

    public List<Transformation> getVariations() {
        return variations;
    }

    public int getSymmetry() {
        return symmetry;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public int getSamples() {
        return samples;
    }

    public int getIterPerSample() {
        return iterPerSample;
    }

    public int getSeed() {
        return seed;
    }

    /**
     * Билдер для конфигурации RenderConfig.
     */
    public static class Builder {
        private int width;
        private int height;
        private Rectangular world;
        private List<ColorTransformation> affine;
        private List<Transformation> variations;
        private int symmetry;
        private int threadCount;
        private int samples;
        private int iterPerSample;
        private int seed;

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setWorld(Rectangular world) {
            this.world = world;
            return this;
        }

        public Builder setAffine(List<ColorTransformation> affine) {
            this.affine = affine;
            return this;
        }

        public Builder setVariations(List<Transformation> variations) {
            this.variations = variations;
            return this;
        }

        public Builder setSymmetry(int symmetry) {
            this.symmetry = symmetry;
            return this;
        }

        public Builder setThreadCount(int threadCount) {
            this.threadCount = threadCount;
            return this;
        }

        public Builder setSamples(int samples) {
            this.samples = samples;
            return this;
        }

        public Builder setIterPerSample(int iterPerSample) {
            this.iterPerSample = iterPerSample;
            return this;
        }

        public Builder setSeed(int seed) {
            this.seed = seed;
            return this;
        }

        public RenderConfig build() {
            return new RenderConfig(this);
        }
    }
}
