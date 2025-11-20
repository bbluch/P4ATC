// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here.
 * Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 * 
 * @author bluch
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
     * @param name
     * @param x
     * @param y
     * @param z
     * @param xWidth
     * @param yWidth
     * @param zWidth
     * @param type
     * @param number
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
     * Get the current value of number.
     * 
     * @return The value of number for this object.
     */
    public int getNumber() {
        return number;
    }


    // ----------------------------------------------------------
    /**
     * Set the value of number for this object.
     * 
     * @param number
     *            The new value for number.
     */
    public void setNumber(int number) {
        this.number = number;
    }


    /**
     * @return boolean based of Air Object isNotValid plus the bird params
     */
    public boolean isNotValid() {
        if (super.isNotValid()) {
            return true;
        }
        if (type == null) {
            return true;
        }
        return number < 1;
    }
    
    /**
     * Returns the full string representation of the Bird object:
     * Bird {name} {x} {y} {z} {xwid} {ywid} {zwid} {type} {number}
     * @return Full Bird string
     */
    @Override
    public String toString() {
        return "Bird " + super.toString() + " " 
             + getType() + " " + getNumber();
    }

}
