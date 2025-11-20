// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here.
 * Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 * 
 * @author bluch
 * @version Nov 14, 2025
 */
public class Balloon extends AirObject {
    // ~ Fields ................................................................
    private String type;
    private int ascentRate;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new Balloon object.
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
     *            type of balloon
     * @param ascentRate
     *            ascent rate if balloon
     */
    public Balloon(
        String name,
        int x,
        int y,
        int z,
        int xWidth,
        int yWidth,
        int zWidth,
        String type,
        int ascentRate) {

        super(name, x, y, z, xWidth, yWidth, zWidth);
        this.type = type;
        this.ascentRate = ascentRate;
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
     * Set the value of type for this object.
     * 
     * @param type
     *            The new value for type.
     */
    public void setType(String type) {
        this.type = type;
    }


    // ----------------------------------------------------------
    /**
     * Get the current value of ascentRate.
     * 
     * @return The value of ascentRate for this object.
     */
    public int getAscentRate() {
        return ascentRate;
    }


    // ----------------------------------------------------------
    /**
     * Set the value of ascentRate for this object.
     * 
     * @param ascentRate
     *            The new value for ascentRate.
     */
    public void setAscentRate(int ascentRate) {
        this.ascentRate = ascentRate;
    }


    /**
     * @return boolean based of Air Object isNotValid plus the balloon params
     */
    public boolean isNotValid() {
        if (super.isNotValid()) {
            return true;
        }
        if (type == null) {
            return true;
        }
        return ascentRate < 0;
    }


    /**
     * Returns the full string representation of the Balloon object:
     * Balloon {name} {x} {y} {z} {xwid} {ywid} {zwid} {type} {ascentRate}
     * 
     * @return Full Balloon string
     */
    @Override
    public String toString() {
        return "Balloon " + super.toString() + " " + getType() + " "
            + getAscentRate();
    }

}
