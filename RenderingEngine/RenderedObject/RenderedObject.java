package RenderingEngine.RenderedObject;

import java.awt.Color;

import java.util.ArrayList;

import RenderingEngine.Graphics.Lighting;
import RenderingEngine.Primitives.Triangle;
import RenderingEngine.Primitives.Vector3;

public class RenderedObject {
    public Transform transform;

    public ArrayList<Triangle> triangles;
    public ArrayList<Vector3> vertices;

    public Color color;

    public boolean shading;

    public Vector3 totalMovement;

    public Lighting lighting;

    public RenderedObject(Vector3 transform) {
        this.transform = new Transform(this, transform);
    }
}
