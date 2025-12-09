// -------------------------------------------------------------------------
/**
 * Bird object class.
 * 
 * @author benblucher
 * @author austink23
 * @version Nov 14, 2025
 */
public class Bird extends AirObject {
    // ~ Fields ................................................................
    private String type;
    private int number;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new Bird object.
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
     * @param type
     *            Type of bird
     * @param number
     *            Number of bird
     */
    public Bird(
        String name,
        int x,
        int y,
        int z,
        int xWidth,
        int yWidth,
        int zWidth,
        String type,
        int number) {

        super(name, x, y, z, xWidth, yWidth, zWidth);
        this.type = type;
        this.number = number;
    }
    // ~Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * Get the current value of type.
     * 
     * @return The value of type for this object.
     */
    public String getType() {
        return type;
    }


    // ----------------------------------------------------------
    /**
     * Get the current value of number.
     * 
     * @return The value of number for this object.
     */
    public int getNumber() {
        return number;
    }


    /**
     * @return boolean based of Air Object isNotValid plus the bird params
     */
    public boolean isNotValid() {
        if (type == null || number < 1) {
            return true;
        }
        return super.isNotValid();
    }


    /**
     * Returns the full string representation of the Bird object:
     * Bird {name} {x} {y} {z} {xwid} {ywid} {zwid} {type} {number}
     * 
     * @return Full Bird string
     */
    @Override
    public String toString() {
        return "Bird " + super.toString() + " " + getType() + " " + getNumber();
    }

}
