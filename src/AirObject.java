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
    private int x;
    private int y;
    private int z;
    private int xWidth;
    private int yWidth;
    private int zWidth;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new AirObject object.
     * 
     * @param x
     *            X coordinate
     * @param y
     *            Y coordinate
     * @param z
     *            Z coordinate
     * @param xWidth
     *            xWidth of object
     * @param yWidth
     *            yWidth of object
     * @param zWidth
     *            zWidth of object
     * @param name
     *            Name of object
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
     * Gets x value
     * 
     * @return x of object
     */
    public int getXorig() {
        return x;
    }


    // ----------------------------------------------------------
    /**
     * Gets y value
     * 
     * @return y of object
     */
    public int getYorig() {
        return y;
    }


    // ----------------------------------------------------------
    /**
     * Gets z value
     * 
     * @return z of object
     */
    public int getZorig() {
        return z;

    }


    // ----------------------------------------------------------
    /**
     * Gets x width.
     * 
     * @return xWidth of object
     */
    public int getXwidth() {
        return xWidth;
    }


    // ----------------------------------------------------------
    /**
     * Gets y width.
     * 
     * @return yWidth of object
     */
    public int getYwidth() {
        return yWidth;
    }


    // ----------------------------------------------------------
    /**
     * Gets z width.
     * 
     * @return zWidth of object
     */
    public int getZwidth() {
        return zWidth;
    }


    // ----------------------------------------------------------
    /**
     * Gets name.
     * 
     * @return name of object
     */
    public String getName() {
        return name;
    }


    /**
     * Determines if object is valid.
     * 
     * @return boolean if AiRObject isNotValid based off params
     */
    public boolean isNotValid() {
        if (x < 0 || x > 1024) {
            return true;
        }
        if (y < 0 || y > 1024) {
            return true;
        }
        if (z < 0 || z > 1024) {
            return true;
        }
        if (name == null) {
            return true;
        }
        if (xWidth < 1 || xWidth > 1025) {
            return true;
        }
        if (yWidth < 1 || yWidth > 1025) {
            return true;
        }
        if (zWidth < 1 || zWidth > 1025) {
            return true;
        }
        if (x + xWidth > 1024) {
            return true;
        }
        if (y + yWidth > 1024) {
            return true;
        }
        return z + zWidth > 1024;
    }


    /**
     * Returns the string representation of the common AirObject fields:
     * name x y z xWidth yWidth zWidth
     * 
     * @return String of common fields
     */
    @Override
    public String toString() {
        // Must match the order of initialization in the constructor
        return name + " " + x + " " + y + " " + z + " " + xWidth + " " + yWidth
            + " " + zWidth;
    }

}
