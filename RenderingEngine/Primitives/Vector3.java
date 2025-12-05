package RenderingEngine.Primitives;

public class Vector3 {
    public static final Vector3 UP = new Vector3(0, 1, 0);
    public static final Vector3 RIGHT = new Vector3(1, 0, 0);
    public static final Vector3 FORWARD = new Vector3(0, 0, 1);
    public static final Vector3 ZERO = new Vector3(0, 0, 0);

    public final double x, y, z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(Vector3 vector) {
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
    }

    public double getMagnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double getSquaredMagnitude() {
        return x * x + y * y + z * z;
    }

    public Vector3 getNormalized() {
        if (this.getSquaredMagnitude() == 1) {
            return this;
        }

        double magnitude = this.getMagnitude();
        return new Vector3(x / magnitude, y / magnitude, z / magnitude);
    }

    public Vector3 add(Vector3 vector) {
        return Vector3.add(this, vector);
    }

    public Vector3 subtract(Vector3 vector) {
        return Vector3.subtract(this, vector);
    }

    public Vector3 multiply(double scaler) {
        return Vector3.multiply(this, scaler);
    }

    public Vector3 rotate(Quaternion quaternion) {
        return Vector3.rotate(this, quaternion);
    }

    public static Vector3 add(Vector3 vector1, Vector3 vector2) {
        return new Vector3(vector1.x + vector2.x, vector1.y + vector2.y, vector1.z + vector2.z);
    }

    public static Vector3 subtract(Vector3 vector1, Vector3 vector2) {
        return new Vector3(vector1.x - vector2.x, vector1.y - vector2.y, vector1.z - vector2.z);
    }

    public static Vector3 multiply(Vector3 vector, double scaler) {
        return new Vector3(vector.x * scaler, vector.y * scaler, vector.z * scaler);
    }

    public static double dotProduct(Vector3 vector1, Vector3 vector2) {
        return vector1.x * vector2.x + vector1.y * vector2.y + vector1.z * vector2.z;
    }

    public static Vector3 crossProduct(Vector3 a, Vector3 b) {
        return new Vector3(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);
    }

    public static Vector3 angleToVector(double yaw, double pitch) {
        double cosPitch = Math.cos(pitch);
        return new Vector3(Math.sin(yaw) * cosPitch, Math.sin(pitch), Math.cos(yaw) * cosPitch);
    }

    public static Vector3 getIntersectionPoint(Vector3 lineDirection, Vector3 linePoint, Plane plane) {
        return Vector3.add(linePoint,
                Vector3.multiply(lineDirection,
                        Vector3.dotProduct(Vector3.subtract(plane.pointOnPlane, linePoint), plane.normal)
                                / Vector3.dotProduct(lineDirection, plane.normal)));
    }

    public static double getAngleBetween(Vector3 a, Vector3 b) {
        return Math.acos(Vector3.dotProduct(a, b) / (a.getMagnitude() * b.getMagnitude()));
    }

    public static Vector3 rotateAroundXaxis(Vector3 point, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vector3(Vector3.dotProduct(new Vector3(1, 0, 0), point),
                Vector3.dotProduct(new Vector3(0, cos, -sin), point),
                Vector3.dotProduct(new Vector3(0, sin, cos), point));
    }

    public static Vector3 rotateAroundYaxis(Vector3 point, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vector3(Vector3.dotProduct(new Vector3(cos, 0, sin), point),
                Vector3.dotProduct(new Vector3(0, 1, 0), point), Vector3.dotProduct(new Vector3(-sin, 0, cos), point));
    }

    public static Vector3 applyMatrix(Matrix3x3 matrix, Vector3 vector) {
        return new Vector3(
                vector.x * matrix.R1C1 + vector.y * matrix.R1C2 + vector.z * matrix.R1C3,
                vector.x * matrix.R2C1 + vector.y * matrix.R2C2 + vector.z * matrix.R2C3,
                vector.x * matrix.R3C1 + vector.y * matrix.R3C2 + vector.z * matrix.R3C3);
    }

    public static Vector3 rotate(Vector3 vector, Quaternion quaternion) {
        final double w = -(quaternion.x * vector.x + quaternion.y * vector.y + quaternion.z * vector.z);
        final double x = quaternion.w * vector.x + quaternion.y * vector.z - quaternion.z * vector.y;
        final double y = quaternion.w * vector.y + quaternion.z * vector.x - quaternion.x * vector.z;
        final double z = quaternion.w * vector.z + quaternion.x * vector.y - quaternion.y * vector.x;

        return new Vector3(
                quaternion.w * x - w * quaternion.x - y * quaternion.z + z * quaternion.y,
                quaternion.w * y - w * quaternion.y - z * quaternion.x + x * quaternion.z,
                quaternion.w * z - w * quaternion.z - x * quaternion.y + y * quaternion.x);
    }
}
