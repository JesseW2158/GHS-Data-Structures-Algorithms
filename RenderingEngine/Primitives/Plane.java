package RenderingEngine.Primitives;

public class Plane {
    public Vector3 normal, pointOnPlane;

    public Plane(Vector3 normal, Vector3 pointOnPlane) {
        this.normal = normal;
        this.pointOnPlane = pointOnPlane;
    }

    public Plane(Vector3 point1, Vector3 point2, Vector3 point3) {
        normal = Vector3.crossProduct(Vector3.subtract(point1, point2), Vector3.subtract(point2, point3))
                .getNormalized();
        pointOnPlane = point1;
    }
}
