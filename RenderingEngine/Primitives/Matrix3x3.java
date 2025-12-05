package RenderingEngine.Primitives;

public class Matrix3x3 {
    public final double R1C1, R1C2, R1C3, R2C1, R2C2, R2C3, R3C1, R3C2, R3C3;

    public Matrix3x3(Vector3 column1, Vector3 column2, Vector3 column3) {
        R1C1 = column1.x;
        R1C2 = column2.x;
        R1C3 = column3.x;

        R2C1 = column1.y;
        R2C2 = column2.y;
        R2C3 = column3.y;

        R3C1 = column1.z;
        R3C2 = column2.z;
        R3C3 = column3.z;
    }

    public Matrix3x3 getInverse() {
        Vector3 col1 = new Vector3(
                (R2C2 * R3C3 - R2C3 * R3C2),
                -(R1C2 * R3C3 - R1C3 * R3C2),
                (R1C2 * R2C3 - R1C3 * R2C2));

        Vector3 col2 = new Vector3(
                -(R2C1 * R3C3 - R2C3 * R3C1),
                (R1C1 * R3C3 - R1C3 * R3C1),
                -(R1C1 * R2C3 - R1C3 * R2C1));

        Vector3 col3 = new Vector3(
                (R2C1 * R3C2 - R2C2 * R3C1),
                -(R1C1 * R3C2 - R1C2 * R3C1),
                (R1C1 * R2C2 - R1C2 * R2C1));

        Matrix3x3 adjugateMatrix = new Matrix3x3(col1, col2, col3);
        double determinant = R1C1 * (R2C2 * R3C3 - R2C3 * R3C2) - R1C2 * (R2C1 * R3C3 - R2C3 * R3C1)
                + R1C3 * (R2C1 * R3C2 - R2C2 * R3C1);

        return Matrix3x3.multiply(adjugateMatrix, 1 / determinant);
    }

    public static Matrix3x3 multiply(Matrix3x3 matrix, double scalar) {
        return new Matrix3x3(
                new Vector3(matrix.R1C1 * scalar, matrix.R1C2 * scalar, matrix.R1C3 * scalar),
                new Vector3(matrix.R2C1 * scalar, matrix.R2C2 * scalar, matrix.R2C3 * scalar),
                new Vector3(matrix.R3C1 * scalar, matrix.R3C2 * scalar, matrix.R3C3 * scalar));
    }

    public String toString() {
        return String.format(
                "\n|%39s\n|%10.2f%10.2f%10.2f%9s\n|%39s\n|%10.2f%10.2f%10.2f%9s\n|%39s\n|%10.2f%10.2f%10.2f%9s\n|%39s\n",
                "|", R1C1, R1C2, R1C3, "|", "|", R2C1, R2C2, R2C3, "|", "|", R3C1, R3C2, R3C3, "|", "|");
    }
}
