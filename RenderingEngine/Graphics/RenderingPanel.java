package RenderingEngine.Graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import RenderingEngine.Primitives.Plane;
import RenderingEngine.Primitives.Quaternion;
import RenderingEngine.Primitives.Vector3;

public class RenderingPanel {
    public ArrayList<Object> objects = new ArrayList<Object>();
    public HashMap<String, Integer> objectIndices = new HashMap<String, Integer>();

    public BufferedImage renderImage;
    public Color backgroundColor;
    public Plane renderPlane;
    public int[] blankImagePixelColorData;
    public ArrayList<Triangle2D>[] sortQueue;
    public ArrayList<Triangle2D> drawQueue;
    public Quaternion pointRotationQuaternion;
    public double pixelsPerUnit;
    public Vector3 camCenterPoint;
    public boolean hasTrianglesToRender;
    public double maxTriangleDistance;
    public double minTriangleDistance;

    public Thread renderingThread;
    public boolean threadRunning;
    public int fps;

    public Camera camera;
    public Vector3 camDirection;
    public Vector3 camPos;
    public double renderPlaneWidth;

    public Lighting lightingObject;

    public double fogStartDistance;
    public double fullFogDistance;
    public boolean fogEnabled = false;
    public int fogR;
    public int fogG;
    public int fogB;

    private TimingHelper totalFrameTime = new TimingHelper("time per frame");
    private TimingHelper trianglesCalculateTime = new TimingHelper("transformation");
    private TimingHelper trianglesPaintTime = new TimingHelper("rasterization");

    public RenderingPanel(int width, int height, Color bgColor) {
        setPreferredSize(new Dimension(width, height));

        backgroundColor = bgColor;
        setBackground(backgroundColor);


        camera = null;
        lightingObject = null;
        gameObjects = new ArrayList<GameObject>();
        sortQeue = null;
        camDirection = Vector3.ZERO;
        camPos = Vector3.ZERO;
        fps = -1;
        drawQeue = new ArrayList<Triangle2D>();
        hasTrianglesToRender = false;


        renderImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        blankImagePixelColorData = new int[width * height];
        Arrays.fill(blankImagePixelColorData, convertToIntRGB(backgroundColor));
    }
}