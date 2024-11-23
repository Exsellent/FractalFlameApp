package backend.academy.FractalFlame.transformations;

public enum NonLinearTransformations {

    // 2D Transformations
    CYLINDER(new CylinderTransformation()), DIAMOND(new DiamondTransformation()), DISC(new DiskTransformation()),
    FISHEYE(new FisheyeTransformation()), HANDKERCHIEF(new HandkerchiefTransformation()),
    HEART(new HeartTransformation()), HORSESHOE(new HorseshoeTransformation()),
    HYPERBOLIC(new HyperbolicTransformation()), SPHERICAL(new SphericalTransformation()),
    SPIRAL(new SpiralTransformation()), SWIRL(new SwirlTransformation()), FLOWER(new FlowerTransformation(7)),
    MANDALA(new MandalaTransformation()), STARBURST(new StarburstTransformation()),

    COMPLEX_DRAGON(new ComplexDragonTransformation());

    private final Transformation transformation;

    NonLinearTransformations(Transformation transformation) {
        this.transformation = transformation;
    }

    public Transformation getTransformation() {
        return transformation;
    }
}
