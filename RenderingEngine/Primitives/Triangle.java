package RenderingEngine.Primitives;

import java.awt.Color;

public class Triangle {
    public Vector3 vertex1;
    public Vector3 vertex2;
    public Vector3 vertex3;

    public Color color;

    public Triangle(Vector3 vertex1, Vector3 vertex2, Vector3 vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
        this.color = Color.LIGHT_GRAY;
    }

    public Plane getPlane() {
        return new Plane(vertex1, vertex2, vertex3);
    }

    public Vector3 getCenter() {
        return new Vector3(
                (vertex1.x + vertex2.x + vertex3.x) / 3,
                (vertex1.y + vertex2.y + vertex3.y) / 3,
                (vertex1.z + vertex2.z + vertex3.z) / 3);
    }
}
