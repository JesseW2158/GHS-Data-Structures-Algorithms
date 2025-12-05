package RenderingEngine;

import RenderingEngine.Primitives.Quaternion;
import RenderingEngine.Primitives.Vector3;

public class RenderingEngine {
    public static void main(String[] args) {
        Quaternion test = new Quaternion(0, new Vector3(1, 0, 0));

        System.out.println(test);
    }
}
