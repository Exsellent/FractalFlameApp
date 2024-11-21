package backend.academy.FractalFlame.transformations;

import backend.academy.FractalFlame.components.Point;

public class ComplexDragonTransformation implements Transformation {
    private static final double THRESHOLD1 = 0.33;
    private static final double THRESHOLD2 = 0.66;
    private static final double COEFF1_X = 0.824074;
    private static final double COEFF1_Y = 0.281428;
    private static final double COEFF1_CONST = -1.882290;
    private static final double COEFF2_X = -0.212346;
    private static final double COEFF2_Y = 0.864198;
    private static final double COEFF2_CONST = -0.110607;
    private static final double COEFF3_X = 0.088272;
    private static final double COEFF3_Y = 0.520988;
    private static final double COEFF3_CONST = 0.785360;
    private static final double COEFF4_X = -0.463889;
    private static final double COEFF4_Y = -0.377778;
    private static final double COEFF4_CONST = 8.095795;
    private static final double COEFF5_X = 0.787879;
    private static final double COEFF5_Y = 0.242424;
    private static final double COEFF5_CONST = 1.0;
    private static final double COEFF6_X = -0.242424;
    private static final double COEFF6_Y = 0.787879;
    private static final double COEFF6_CONST = 1.0;

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double newX;
        double newY;

        double rand = Math.random();
        if (rand < THRESHOLD1) {
            newX = COEFF1_X * x + COEFF1_Y * y + COEFF1_CONST;
            newY = COEFF2_X * x + COEFF2_Y * y + COEFF2_CONST;
        } else if (rand < THRESHOLD2) {
            newX = COEFF3_X * x + COEFF3_Y * y + COEFF3_CONST;
            newY = COEFF4_X * x + COEFF4_Y * y + COEFF4_CONST;
        } else {
            newX = COEFF5_X * x + COEFF5_Y * y + COEFF5_CONST;
            newY = COEFF6_X * x + COEFF6_Y * y + COEFF6_CONST;
        }

        return new Point(newX, newY);
    }
}
