// -------------------------------------------------------------------------
/**
 * AirPlane Object Class.
 * 
 * @author benblucher, austink23
 * @version Nov 14, 2025
 */
public class AirPlane extends AirObject {

    // ~ Fields ................................................................
    private String carrier;
    private int flightNumber;
    private int numEngines;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new AirPlane object.
     * 
     * @param name
     * @param x
     * @param y
     * @param z
     * @param xWidth
     * @param yWidth
     * @param zWidth
     * @param carrier
     * @param flightNumber
     * @param numEngines
     */
    public AirPlane(
        String name,
        int x,
        int y,
        int z,
        int xWidth,
        int yWidth,
        int zWidth,
        String carrier,
        int flightNumber,
        int numEngines) {

        super(name, x, y, z, xWidth, yWidth, zWidth);

        this.carrier = carrier;
        this.flightNumber = flightNumber;
        this.numEngines = numEngines;
    }


    // ~Public Methods ........................................................
    // ----------------------------------------------------------
    /**
     * Getter for carrier.
     * 
     * @return carrier for AirPlane object
     */
    public String getCarrier() {
        return carrier;
    }


    // ----------------------------------------------------------
    /**
     * Getter for flight number.
     * 
     * @return flight number for plane
     */
    public int getFlightNumber() {
        return flightNumber;
    }


    // ----------------------------------------------------------
    /**
     * Getter for number of engines.
     * 
     * @return number of engines of plane
     */
    public int getNumEngines() {
        return numEngines;
    }


    /**
     * @return boolean based of Air Object isNotValid plus the airplane params
     */
    public boolean isNotValid() {
        if (super.isNotValid()) {
            return true;
        }
        if (flightNumber < 1) {
            return true;
        }
        if (numEngines < 1) {
            return true;
        }
        if (carrier == null) {
            return true;
        }
        return false;
    }

}
