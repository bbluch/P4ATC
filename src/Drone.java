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
     * @param name
     * @param x
     * @param y
     * @param z
     * @param xWidth
     * @param yWidth
     * @param zWidth
     * @param brand
     * @param numEngines
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

}
