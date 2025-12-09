// -------------------------------------------------------------------------
/**
 * Rocket object class.
 * 
 * @author benblucher, austink23
 * @version Nov 14, 2025
 */
public class Rocket extends AirObject {
    // ~ Fields ................................................................
    private int ascentRate;
    private double trajectory;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new Rocket object.
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
     * @param ascentRate
     *            ascent rate of rocket
     * @param trajectory
     *            Trajectory of rocket
     */
    public Rocket(
        String name,
        int x,
        int y,
        int z,
        int xWidth,
        int yWidth,
        int zWidth,
        int ascentRate,
        double trajectory) {

        super(name, x, y, z, xWidth, yWidth, zWidth);
        this.ascentRate = ascentRate;
        this.trajectory = trajectory;
    }
    // ~Public Methods ........................................................


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
     * Get the current value of trajectory.
     * 
     * @return The value of trajectory for this object.
     */
    public double getTrajectory() {
        return trajectory;
    }




    /**
     * @return boolean based of Air Object isNotValid plus the rocket params
     */
    public boolean isNotValid() {
        if (ascentRate < 0 || trajectory < 0) {
            return true;
        }
        return super.isNotValid();
    }


    /**
     * Returns the full string representation of the Rocket object:
     * Rocket {name} {x} {y} {z} {xwid} {ywid} {zwid} {ascentRate} {trajectory}
     * 
     * @return Full Rocket string
     */
    @Override
    public String toString() {
        return "Rocket " + super.toString() + " " + getAscentRate() + " "
            + getTrajectory();
    }

}
