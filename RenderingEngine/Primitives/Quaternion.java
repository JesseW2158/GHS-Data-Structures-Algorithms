package RenderingEngine.Primitives;

public class Quaternion {
    public static final Quaternion IDENTITY = new Quaternion(0, new Vector3(0, 0, 1));

    public final double w, x, y, z;

    public Quaternion(double angle, Vector3 axis) {
        axis = (axis.getSquaredMagnitude() != 1) ? axis.getNormalized() : axis;
        angle = Math.sin(angle / 2);

        w = Math.sqrt(1 - angle * angle);

        double sinAngle = angle;

        x = axis.x * sinAngle;
        y = axis.y * sinAngle;
        z = axis.z * sinAngle;
    }

    public Quaternion(double w, double x, double y, double z) {
        this.w = w;

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Quaternion getInverse() {
        return new Quaternion(w, -x, -y, -z);
    }

    public static Quaternion multiply(Quaternion quaternion1, Quaternion quaternion2) {
        return new Quaternion(
                quaternion1.w * quaternion2.w - (quaternion1.x * quaternion2.x + quaternion1.y * quaternion2.y
                        + quaternion1.z * quaternion2.z),
                quaternion2.w * quaternion1.x + quaternion1.w * quaternion2.x + quaternion1.y * quaternion2.z
                        - quaternion1.z * quaternion2.y,
                quaternion2.w * quaternion1.y + quaternion1.w * quaternion2.y + quaternion1.z * quaternion2.x
                        - quaternion1.x * quaternion2.z,
                quaternion2.w * quaternion1.z + quaternion1.w * quaternion2.z + quaternion1.x * quaternion2.y
                        - quaternion1.y * quaternion2.x);
    }

    public String toString() {
        return new String(String.format("[%.3f, %.3f, %.3f, %.3f]", w, x, y, z));
    }
}
