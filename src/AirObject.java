// -------------------------------------------------------------------------
/**
 * Air Object superclass.
 * 
 * @author benblucher, austink23
 * @version Nov 14, 2025
 */
public class AirObject {
    // ~ Fields ................................................................
    private String name;
    private int x, y, z, xWidth, yWidth, zWidth;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new AirObject object.
     * 
     * @param x
     * @param y
     * @param z
     * @param xWidth
     * @param yWidth
     * @param zWidth
     * @param name
     */
    public AirObject(
        String name,
        int x,
        int y,
        int z,
        int xWidth,
        int yWidth,
        int zWidth) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.xWidth = xWidth;
        this.yWidth = yWidth;
        this.zWidth = zWidth;
    }


    // ~Public Methods ........................................................
    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return x of object
     */
    public int getXorig() {
        return x;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return y of object
     */
    public int getYorig() {
        return y;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return z of object
     */
    public int getZorig() {
        return z;

    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return xWidth of object
     */
    public int getXwidth() {
        return xWidth;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return yWidth of object
     */
    public int getYwidth() {
        return yWidth;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return zWidth of object
     */
    public int getZwidth() {
        return zWidth;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return name of object
     */
    public String getName() {
        return name;
    }
}
