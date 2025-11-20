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


    /**
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
     * @return String of common fields
     */
    @Override
    public String toString() {
        // Must match the order of initialization in the constructor
        return name + " " + x + " " + y + " " + z + " "
             + xWidth + " " + yWidth + " " + zWidth;
    }

}
