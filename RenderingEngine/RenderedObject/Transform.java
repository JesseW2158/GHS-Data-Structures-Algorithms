package RenderingEngine.RenderedObject;

import RenderingEngine.Primitives.Quaternion;
import RenderingEngine.Primitives.Vector3;

public class Transform {
    public RenderedObject renderedObject;

    public Vector3 position;
    public Vector3 forward;
    public Vector3 right;
    public Vector3 up;

    public Quaternion rotation;

    public Transform(RenderedObject renderedObject, Vector3 position) {
        this.renderedObject = renderedObject;
        this.position = position;
        this.rotation = Quaternion.IDENTITY;

        this.forward = new Vector3(0,0 ,1);
        this.right = new Vector3(1,0 ,0);
        this.up = new Vector3(0,1 ,0);
    }

    
}
