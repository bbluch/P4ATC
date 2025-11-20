// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here.
 * Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 * 
 * @author benblucher, austink23
 * @version Nov 14, 2025
 */
public class Drone extends AirObject {
    // ~ Fields ................................................................
    private String brand;
    private int numEngines;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new Drone object.
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
     * @param brand
     *            Brand of drone
     * @param numEngines
     *            Number of engines
     */
    public Drone(
        String name,
        int x,
        int y,
        int z,
        int xWidth,
        int yWidth,
        int zWidth,
        String brand,
        int numEngines) {

        super(name, x, y, z, xWidth, yWidth, zWidth);
        this.brand = brand;
        this.numEngines = numEngines;
    }
    // ~Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * Get the current value of brand.
     * 
     * @return The value of brand for this object.
     */
    public String getBrand() {
        return brand;
    }


    // ----------------------------------------------------------
    /**
     * Set the value of brand for this object.
     * 
     * @param brand
     *            The new value for brand.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }


    // ----------------------------------------------------------
    /**
     * Get the current value of numEngines.
     * 
     * @return The value of numEngines for this object.
     */
    public int getNumEngines() {
        return numEngines;
    }


    // ----------------------------------------------------------
    /**
     * Set the value of numEngines for this object.
     * 
     * @param numEngines
     *            The new value for numEngines.
     */
    public void setNumEngines(int numEngines) {
        this.numEngines = numEngines;
    }


    /**
     * @return boolean based of Air Object isNotValid plus the drone params
     */
    public boolean isNotValid() {
        if (super.isNotValid()) {
            return true;
        }
        if (brand == null) {
            return true;
        }
        return numEngines < 1;
    }


    /**
     * Returns the full string representation of the Drone object:
     * Drone {name} {x} {y} {z} {xwid} {ywid} {zwid} {brand} {#engines}
     * 
     * @return Full Drone string
     */
    @Override
    public String toString() {
        return "Drone " + super.toString() + " " + getBrand() + " "
            + getNumEngines();
    }

}
